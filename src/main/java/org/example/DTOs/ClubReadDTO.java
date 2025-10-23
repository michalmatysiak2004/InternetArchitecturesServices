package org.example.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ClubReadDTO {
    private UUID id;
    private String name;
    private String country;
    private Integer titles;
}
