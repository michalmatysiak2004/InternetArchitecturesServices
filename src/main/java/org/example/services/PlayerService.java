package org.example.services;


import org.example.entities.Club;
import org.example.entities.Player;
import org.example.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    PlayerRepository playerRepository;


    public List<Player> fetchPlayerList(){
        return (List<Player>) playerRepository.findAll();
    }
    public List<Player> findPlayersByClub(Club club){
        return (List<Player>) playerRepository.findByClub(club);
    }

}
