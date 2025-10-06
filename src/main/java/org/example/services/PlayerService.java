package org.example.services;


import jakarta.transaction.Transactional;
import org.example.entities.Club;
import org.example.entities.Player;
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
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ClubRepository clubRepository;

    public List<Player> fetchPlayerList(){
        return (List<Player>) playerRepository.findAll();
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

    @Transactional
    public void deletePlayer(String firstname, String lastname) {
        Optional<Player> optionalPlayer = playerRepository.findByFirstNameAndLastName(firstname, lastname);

        if (optionalPlayer.isPresent()) {
            playerRepository.delete(optionalPlayer.get());
            System.out.println("Gracz " + firstname + " " + lastname + " usuniety.");
        } else {
            System.out.println("Gracz " + firstname + " " + lastname + " nie znaleziony");
        }
    }

}
