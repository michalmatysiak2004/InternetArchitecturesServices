package com.example.club_service.controller;

import com.example.club_service.request.ClubRequest;
import com.example.club_service.response.ClubResponse;
import com.example.club_service.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;


    @Autowired
    public ClubController( ClubService clubService) {

        this.clubService = clubService;
    }
    //ok
    @GetMapping
    public List<ClubResponse> allClubs(){
        return clubService.getAllClubs();
    }

    //ok
    @PostMapping
    public ClubResponse addClub(@RequestBody ClubRequest clubCreateDTO){
        return clubService.saveClub(clubCreateDTO);
    }

    @GetMapping("/{uuid}")
    public ClubResponse getClub(@PathVariable UUID uuid){
        return clubService.findById(uuid);
    }

    @PutMapping("/{uuid}")
    public ClubResponse updateClub(@PathVariable UUID uuid, @RequestBody ClubRequest clubCreateDTO){
        return clubService.updateClub(uuid,clubCreateDTO);
    }

    @DeleteMapping("/{uuid}")
    public void deleteCLub(@PathVariable UUID uuid){
        clubService.deleteClubById(uuid);
    }


}