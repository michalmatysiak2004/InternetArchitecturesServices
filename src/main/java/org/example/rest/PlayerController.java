package org.example.rest;

import org.example.DTOs.PlayerCreateDTO;
import org.example.DTOs.PlayerReadDTO;
import org.example.entities.Player;
import org.example.mapper.PlayerMapper;
import org.example.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<PlayerReadDTO> allPlayers(){
        return playerService.fetchPlayerList();
    }

    @PostMapping("/players")
    public PlayerReadDTO addPlayer(@RequestBody PlayerCreateDTO playerCreateDTO){
        return playerService.savePlayerfromDTO(playerCreateDTO);
    }

    @PutMapping("/players/{uuid}")
    public PlayerReadDTO updatePlayer(@RequestBody PlayerCreateDTO playerCreateDTO, @PathVariable UUID uuid){
        return playerService.updatePlayer(playerCreateDTO, uuid);
    }

    @GetMapping("/players/{uuid}")
    public PlayerReadDTO getPlayer(@PathVariable UUID uuid){
        return playerService.findById(uuid);
    }
    //do przetestowania
    @DeleteMapping("/players/{uuid}")
    public void deletePlayer(@PathVariable UUID uuid){
        playerService.deletePlayer(uuid);
    }
}
