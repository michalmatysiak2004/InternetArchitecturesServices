package org.example.rest;

import org.example.DTOs.ClubCreateDTO;
import org.example.DTOs.ClubReadDTO;
import org.example.DTOs.PlayerReadDTO;
import org.example.services.ClubService;
import org.example.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClubController {

    private ClubService clubService;
    private PlayerService playerService;

    @Autowired
    public ClubController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }
    //ok
    @GetMapping("/clubs")
    public List<ClubReadDTO> allClubs(){
        return clubService.getAllClubs();
    }

    //ok
    @PostMapping("/clubs")
    public ClubReadDTO addClub(@RequestBody ClubCreateDTO clubCreateDTO){
        return clubService.saveClub(clubCreateDTO);
    }

    @GetMapping("/clubs/{uuid}")
    public ClubReadDTO getClub(@PathVariable UUID uuid){
        return clubService.findById(uuid);
    }

    @PutMapping("/clubs/{uuid}")
    public ClubReadDTO updateClub(@PathVariable UUID uuid, @RequestBody ClubCreateDTO clubCreateDTO){
        return clubService.updateClub(uuid,clubCreateDTO);
    }

    @GetMapping("/clubs/{uuid}/players")
    public List<PlayerReadDTO> getPlayersFromClub(@PathVariable UUID uuid){
        return playerService.findPlayersByClub(uuid);
    }

    @DeleteMapping("/clubs/{uuid}")
    public void deleteCLub(@PathVariable UUID uuid){
        clubService.deleteClubById(uuid);
    }


}
