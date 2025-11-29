package com.example.player_service.service;


import com.example.player_service.entity.Player;
import com.example.player_service.exception.ResourceNotFoundException;
import com.example.player_service.mapper.PlayerMapper;
import com.example.player_service.repository.PlayerRepository;
import com.example.player_service.request.PlayerRequest;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.player_service.response.PlayerResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    private final RestTemplate restTemplate;

    PlayerRepository playerRepository;

    PlayerMapper playerMapper;


    @Value("${club.service.url}")
    private String clubServiceUrl;

    @Autowired
    public PlayerService(RestTemplateBuilder builder, PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.restTemplate = builder.build();

    }

 

    public List<PlayerResponse> fetchPlayerList(){
        List<Player> players =  playerRepository.findAll();
        List<PlayerResponse> PlayerResponseS = playerMapper.mapToDTO(players);
        return PlayerResponseS;
    }

    public List<PlayerResponse> findPlayersByClub(UUID uuid){
        if(!isClubValid(uuid)){
            throw new ResourceNotFoundException("Club not found");
        }

        List<Player> players = playerRepository.findByClubID(uuid);

        return playerMapper.mapToDTO(players);
    }

    private boolean isClubValid(UUID clubId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(clubServiceUrl + "/" + clubId, Void.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
        return false;
    }

}

    public Player createEntity(PlayerRequest playerCreateDTO){
        if(!isClubValid(playerCreateDTO.getClubID())){
            throw new ResourceNotFoundException("Club not found - you cannot add player to non-existing club");
        }



        Player player =  Player.builder()
                .id(UUID.randomUUID())
                .firstName(playerCreateDTO.getFirstName())
                .lastName(playerCreateDTO.getLastName())
                .country(playerCreateDTO.getCountry())
                .goals(playerCreateDTO.getGoals())
                .clubID(playerCreateDTO.getClubID())
                .build();


        return player;


    }

    public PlayerResponse savePlayerfromDTO(PlayerRequest playerCreateDTO){
        Player dbPlayer = createEntity(playerCreateDTO);
        playerRepository.save(dbPlayer);
        PlayerResponse PlayerResponse = playerMapper.mapToDTO(dbPlayer);
        return  PlayerResponse;
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

    public PlayerResponse updatePlayer(PlayerRequest playerCreateDTO, UUID uuid) {
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
        if(playerCreateDTO.getClubID() != null){

            if(!isClubValid(playerCreateDTO.getClubID())){
                throw new ResourceNotFoundException("Club not found - you cannoit add player to the club doesnt exists");
            }
            dbplayer.setClubID(playerCreateDTO.getClubID());
        }
        playerRepository.save(dbplayer);
        return playerMapper.mapToDTO(dbplayer);
    }

    public PlayerResponse findById(UUID uuid ) {
        Optional<Player> player = playerRepository.findById(uuid);

        if (player.isEmpty()){
            throw new ResourceNotFoundException("Player not found");
        }
        Player dbplayer = player.get();

        return  playerMapper.mapToDTO(dbplayer);
    }

    @Transactional
    public void deletePlayer(UUID uuid) {
        Optional<Player> player = playerRepository.findById(uuid);

        if (player.isEmpty()){
            throw new ResourceNotFoundException("Player not found");
        }
        Player dbplayer = player.get();
        playerRepository.delete(dbplayer);
    }

    @Transactional
    public void deleteAllPlayersFromClub(UUID clubID){


        List<Player> players = playerRepository.findByClubID(clubID);
        playerRepository.deleteAll(players);

    }


}