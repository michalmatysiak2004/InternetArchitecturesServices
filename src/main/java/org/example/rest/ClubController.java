package org.example.rest;

import org.example.DTOs.ClubReadDTO;
import org.example.services.ClubService;
import org.example.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/clubs")
    public List<ClubReadDTO> allClubs(){
        return clubService.getAllClubs();
    }


}
