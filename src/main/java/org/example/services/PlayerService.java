package org.example.services;


import jakarta.transaction.Transactional;
import org.example.DTOs.PlayerCreateDTO;
import org.example.DTOs.PlayerReadDTO;
import org.example.entities.Club;
import org.example.entities.Player;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ClubMapper;
import org.example.mapper.PlayerMapper;
import org.example.repositories.ClubRepository;
import org.example.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    PlayerRepository playerRepository;
    ClubRepository clubRepository;
    PlayerMapper playerMapper;


    @Autowired
    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerReadDTO> fetchPlayerList(){
        List<Player> players =  playerRepository.findAll();

        List<PlayerReadDTO> playerReadDTOS = playerMapper.mapToDTO(players);



        return playerReadDTOS;
    }

    public List<Player> findPlayersByClubname(String clubname){
        Optional<Club> optionalClub = clubRepository.findByName(clubname);

        if (optionalClub.isEmpty()) {
            System.out.println("Klub  nie znaleziony" + clubname);
            return Collections.emptyList();
        }
        Club club = optionalClub.get();

        return (List<Player>) playerRepository.findByClub(club);
    }
    public List<PlayerReadDTO> findPlayersByClub(UUID uuid){
        Optional<Club> optionalClub = clubRepository.findById(uuid);

        if (optionalClub.isEmpty()) {
            throw new ResourceNotFoundException("Club not found");
        }
        Club club = optionalClub.get();
        List<Player> players =  playerRepository.findByClub(club);

        return playerMapper.mapToDTO(players);
    }

    public Player createEntity(PlayerCreateDTO playerCreateDTO){
        Optional<Club> optionalClub = clubRepository.findByName(playerCreateDTO.getClubName());

        if (optionalClub.isEmpty()) {
            throw new ResourceNotFoundException("Nie znaleziono klubu");
        }
        Club club = optionalClub.get();

        Player player =  Player.builder()
                .id(UUID.randomUUID())
                .firstName(playerCreateDTO.getFirstName())
                .lastName(playerCreateDTO.getLastName())
                .country(playerCreateDTO.getCountry())
                .goals(playerCreateDTO.getGoals())
                .club(club)
                .build();


        return player;


    }

    public void savePlayer(String firstname,String lastname, String country, int goals, String clubname){
        Optional<Club> optionalClub = clubRepository.findByName(clubname);

        if (optionalClub.isEmpty()) {
            System.out.println("Klub  nie znaleziony" + clubname + ". Gracz nie dodany.");
            return;
        }
        Club club = optionalClub.get();
        playerRepository.save(new Player(UUID.randomUUID(),firstname,lastname,country,goals,club));
        System.out.println("Gracz " + firstname + " " + lastname + " dodany.");
    }

    public PlayerReadDTO savePlayerfromDTO(PlayerCreateDTO playerCreateDTO){
        Player dbPlayer = createEntity(playerCreateDTO);
        playerRepository.save(dbPlayer);
        PlayerReadDTO playerReadDTO = playerMapper.mapToDTO(dbPlayer);
        return  playerReadDTO;
    }

    @Transactional
    public void deletePlayer2(String firstname, String lastname) {
        Optional<Player> optionalPlayer = playerRepository.findByFirstNameAndLastName(firstname, lastname);

        if (optionalPlayer.isPresent()) {
            playerRepository.delete(optionalPlayer.get());
            System.out.println("Gracz " + firstname + " " + lastname + " usuniety.");
        } else {
            System.out.println("Gracz " + firstname + " " + lastname + " nie znaleziony");
        }
    }

    public PlayerReadDTO updatePlayer(PlayerCreateDTO playerCreateDTO, UUID uuid) {
        Optional<Player> player = playerRepository.findById(uuid);

        if (player.isEmpty()){
            throw new ResourceNotFoundException("Player not found"); // ewentualnie zrobic player not found exception swoje
        }
        Player dbplayer = player.get();

        if(playerCreateDTO.getFirstName() != null){
            dbplayer.setFirstName(playerCreateDTO.getFirstName());
        }
        if(playerCreateDTO.getLastName() != null){
            dbplayer.setLastName(playerCreateDTO.getLastName());
        }
        if(playerCreateDTO.getGoals() != null){
            dbplayer.setGoals(playerCreateDTO.getGoals());
        }
        if(playerCreateDTO.getCountry() != null){
            dbplayer.setCountry(playerCreateDTO.getCountry());
        }
        if(playerCreateDTO.getClubName() != null){
            Optional<Club> optionalClub = clubRepository.findByName(playerCreateDTO.getClubName());

            if (optionalClub.isEmpty()) {
               throw new ResourceNotFoundException("Club not found");
            }
            Club club = optionalClub.get();
            dbplayer.setClub(club);
        }

        playerRepository.save(dbplayer);
        System.out.println("Zaktualizowano gracza - " + dbplayer.toString());
        return playerMapper.mapToDTO(dbplayer);
    }

    public PlayerReadDTO findById(UUID uuid ) {
        Optional<Player> player = playerRepository.findById(uuid);

        if (player.isEmpty()){
            throw new ResourceNotFoundException("Player not found"); // ewentualnie zrobic player not found exception swoje
        }
        Player dbplayer = player.get();

        return  playerMapper.mapToDTO(dbplayer);
    }

    @Transactional
    public void deletePlayer(UUID uuid) {
        Optional<Player> player = playerRepository.findById(uuid);

        if (player.isEmpty()){
            throw new ResourceNotFoundException("Player not found"); // ewentualnie zrobic player not found exception swoje
        }
        Player dbplayer = player.get();
        playerRepository.delete(dbplayer);
    }
}
