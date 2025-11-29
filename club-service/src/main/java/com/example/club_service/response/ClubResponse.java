package com.example.club_service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ClubResponse {
    private UUID id;
    private String name;
    private String country;
    private Integer titles;
}
