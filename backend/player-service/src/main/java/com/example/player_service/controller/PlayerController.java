package com.example.player_service.controller;

import com.example.player_service.request.PlayerRequest;
import com.example.player_service.response.PlayerResponse;
import com.example.player_service.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<PlayerResponse> allPlayers(){
        return playerService.fetchPlayerList();
    }

    @PostMapping
    public PlayerResponse addPlayer(@RequestBody PlayerRequest playerCreateDTO){
        return playerService.savePlayerfromDTO(playerCreateDTO);
    }

    @PutMapping("/{uuid}")
    public PlayerResponse updatePlayer(@RequestBody PlayerRequest playerCreateDTO, @PathVariable UUID uuid){
        return playerService.updatePlayer(playerCreateDTO, uuid);
    }

    @GetMapping("/{uuid}")
    public PlayerResponse  getPlayer(@PathVariable UUID uuid){
        return playerService.findById(uuid);
    }


    @DeleteMapping("/{uuid}")
    public void deletePlayer(@PathVariable UUID uuid){
        playerService.deletePlayer(uuid);
    }

    @GetMapping("/by-club/{clubId}")
    public List<PlayerResponse> getPlayersByClubID(@PathVariable UUID clubId)
    {
        return playerService.findPlayersByClub(clubId);
    }


    @DeleteMapping("/by-club/{clubId}")
    public ResponseEntity<Void> deletePlayersByClub(@PathVariable UUID clubId) {
        playerService.deleteAllPlayersFromClub(clubId);
        return ResponseEntity.noContent().build();
    }


}