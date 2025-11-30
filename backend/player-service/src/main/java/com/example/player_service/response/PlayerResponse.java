package com.example.player_service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PlayerResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String country;
    private Integer goals;

}
