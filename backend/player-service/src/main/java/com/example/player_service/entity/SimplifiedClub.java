package com.example.player_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@Table(name="Club")
public class SimplifiedClub {

    @Id
    UUID id;
    String name;
}
