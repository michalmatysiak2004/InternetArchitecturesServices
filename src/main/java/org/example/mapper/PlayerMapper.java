package org.example.mapper;

import org.example.DTOs.PlayerCreateDTO;
import org.example.DTOs.PlayerReadDTO;
import org.example.entities.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PlayerMapper {
    public PlayerReadDTO mapToDTO (Player player){
        PlayerReadDTO playerReadDTO = PlayerReadDTO.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .country(player.getCountry())
                .goals(player.getGoals())
                .clubName(player.getClub().getName())
                .build();
        return playerReadDTO;
    }

    public List<PlayerReadDTO> mapToDTO (List<Player> players){
        List<PlayerReadDTO> playerReadDTOS = players.stream()
                .map(this::mapToDTO)
                .toList();
        return playerReadDTOS;
    }





}
