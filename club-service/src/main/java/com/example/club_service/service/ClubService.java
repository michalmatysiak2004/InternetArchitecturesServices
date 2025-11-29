package com.example.club_service.service;


import com.example.club_service.entity.Club;
import com.example.club_service.exception.ResourceNotFoundException;
import com.example.club_service.mapper.ClubMapper;
import com.example.club_service.repository.ClubRepository;
import com.example.club_service.request.ClubRequest;
import com.example.club_service.response.ClubResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final RestTemplate restTemplate;

    @Value("${player.service.url}")
    private String playerServiceUrl;

    @Autowired
    public ClubService(ClubRepository clubRepository, ClubMapper clubMapper, RestTemplateBuilder builder) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
        this.restTemplate = builder.build();
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

        try {
            restTemplate.delete(playerServiceUrl + "/by-club/" + id);
        } catch (Exception e) {

            System.err.println("Nie udało się powiadomić PlayerService o usunięciu klubu");
        }

    }

    public void clearRepository(){
        clubRepository.deleteAll();
    }
    public ClubResponse findById(UUID uuid){
        Optional<Club> club =  clubRepository.findById(uuid);
        if(club.isEmpty()){
            throw new ResourceNotFoundException("Club not found");
        }

        return clubMapper.mapToDTO(club.get());
    }

    public List<ClubResponse> getAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubMapper.mapToDTO(clubs);
    }

    public ClubResponse saveClub(ClubRequest clubCreateDTO) {
        Club dbClub = createEntity(clubCreateDTO);
        clubRepository.save(dbClub);
        return clubMapper.mapToDTO(dbClub);
    }

    public Club createEntity(ClubRequest clubCreateDTO){
        return Club.builder()
                .id(UUID.randomUUID())
                .name(clubCreateDTO.getName())
                .country(clubCreateDTO.getCountry())
                .titles(clubCreateDTO.getTitles())
                .build();

    }


    public ClubResponse updateClub(UUID uuid, ClubRequest clubCreateDTO) {
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