package com.example.club_service.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClubRequest {
    private String name;
    private String country;
    private Integer titles;
}
