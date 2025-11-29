package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.DTOs.ClubCreateDTO;
import org.example.DTOs.ClubReadDTO;
import org.example.entities.Club;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ClubMapper;
import org.example.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ClubService {

    private ClubRepository clubRepository;
    private ClubMapper clubMapper;
    @Autowired
    public ClubService(ClubRepository clubRepository, ClubMapper clubMapper) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
    }



    private final ClubRepository clubRepository;


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
    public ClubReadDTO findById(UUID uuid){
       Optional<Club> club =  clubRepository.findById(uuid);
       if(club.isEmpty()){
           throw new ResourceNotFoundException("Club not found");
       }

       return clubMapper.mapToDTO(club.get());
    }

    public List<ClubReadDTO> getAllClubs() {
            List<Club> clubs = clubRepository.findAll();
            return clubMapper.mapToDTO(clubs);
    }

    public ClubReadDTO saveClub(ClubCreateDTO clubCreateDTO) {
        Club dbClub = createEntity(clubCreateDTO);
        clubRepository.save(dbClub);
        return clubMapper.mapToDTO(dbClub);
    }

    public Club createEntity(ClubCreateDTO clubCreateDTO){
        return Club.builder()
                .id(UUID.randomUUID())
                .name(clubCreateDTO.getName())
                .country(clubCreateDTO.getCountry())
                .titles(clubCreateDTO.getTitles())
                .players(new ArrayList<>())
                .build();

    }


    public ClubReadDTO updateClub(UUID uuid, ClubCreateDTO clubCreateDTO) {
        Optional<Club> club = clubRepository.findById(uuid);

        if(club.isEmpty()){
            throw new ResourceNotFoundException("Club not found");
        }

        Club dbClub = club.get();

        if(clubCreateDTO.getName() != null){
            dbClub.setName(clubCreateDTO.getName());
        }
        if(clubCreateDTO.getCountry() != null){
            dbClub.setCountry(clubCreateDTO.getCountry());
        }
        if(clubCreateDTO.getTitles() != null){
            dbClub.setTitles(clubCreateDTO.getTitles());
        }

        clubRepository.save(dbClub);

        return clubMapper.mapToDTO(dbClub);

    }
}
