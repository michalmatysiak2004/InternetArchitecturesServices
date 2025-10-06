package org.example.services;

import org.example.entities.Club;
import org.example.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;
    // do updatu podczas commandlinera


    public List<Club> fetchClubList(){
        return (List<Club>) clubRepository.findAll();
    }
    public void deleteClubById(UUID id){
        clubRepository.deleteById(id);
    }
    public void clearRepository(){
        clubRepository.deleteAll();
    }
}
