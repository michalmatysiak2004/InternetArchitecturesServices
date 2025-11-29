package org.example.mapper;

import org.example.DTOs.ClubReadDTO;
import org.example.entities.Club;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubMapper {


    public ClubReadDTO mapToDTO(Club club){
        return ClubReadDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .country(club.getCountry())
                .titles(club.getTitles())
                .build();
    }

    public List<ClubReadDTO> mapToDTO(List<Club> clubs){
        List<ClubReadDTO> clubReadDTOs = clubs.stream()
                .map(this::mapToDTO)
                .toList();
        return clubReadDTOs;
    }

}
