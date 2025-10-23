package org.example.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClubCreateDTO {
    private String name;
    private String country;
    private Integer titles;
}
