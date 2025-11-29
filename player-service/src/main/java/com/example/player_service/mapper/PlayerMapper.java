
package com.example.player_service.mapper;


import com.example.player_service.entity.Player;
import com.example.player_service.response.PlayerResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PlayerMapper {
    public PlayerResponse mapToDTO (Player player){
        PlayerResponse playerReadDTO = PlayerResponse.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .country(player.getCountry())
                .goals(player.getGoals())
                .build();
        return playerReadDTO;
    }

    public List<PlayerResponse> mapToDTO (List<Player> players){
        List<PlayerResponse> playerReadDTOS = players.stream()
                .map(this::mapToDTO)
                .toList();
        return playerReadDTOS;
    }





}