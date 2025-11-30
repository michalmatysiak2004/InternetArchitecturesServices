package com.example.player_service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PlayerRequest {

    private String firstName;
    private String lastName;
    private String country;
    private Integer goals;
    private UUID clubID;
}
