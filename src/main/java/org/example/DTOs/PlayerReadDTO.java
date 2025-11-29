package org.example.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PlayerReadDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String country;
    private Integer goals;
    private String clubName;

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Goals: " + goals + " | Club: " + clubName;
    }



}