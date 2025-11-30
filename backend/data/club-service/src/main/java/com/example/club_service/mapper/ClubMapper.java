package com.example.club_service.mapper;


import com.example.club_service.entity.Club;
import com.example.club_service.response.ClubResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubMapper {


    public ClubResponse mapToDTO(Club club){
        return ClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .country(club.getCountry())
                .titles(club.getTitles())
                .build();
    }

    public List<ClubResponse> mapToDTO(List<Club> clubs){
        List<ClubResponse> clubReadDTOs = clubs.stream()
                .map(this::mapToDTO)
                .toList();
        return clubReadDTOs;
    }

}