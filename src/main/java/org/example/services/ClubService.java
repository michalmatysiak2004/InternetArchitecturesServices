package org.example.services;

import org.example.DTOs.ClubReadDTO;
import org.example.entities.Club;
import org.example.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> fetchClubList(){
        return (List<Club>) clubRepository.findAll();
    }
    public void deleteClubById(UUID id){
        clubRepository.deleteById(id);
    }

    public void clearRepository(){
        clubRepository.deleteAll();
    }

    public List<ClubReadDTO> getAllClubs() {
            List<Club> clubs = clubRepository.findAll();
            clubsDTO = clubMapper
    }
}
