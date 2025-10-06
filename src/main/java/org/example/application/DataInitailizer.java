package org.example.application;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.entities.Club;
import org.example.entities.Player;
import org.example.repositories.ClubRepository;
import org.example.repositories.PlayerRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataInitailizer {

    private PlayerRepository playerRepository;
    private ClubRepository clubRepository;

    @PostConstruct
    void init() {
        Club realmadryt = Club.builder()
                .id(UUID.randomUUID())
                .name("Real Madrid")
                .country("Spain")
                .titles(95)
                .players(new ArrayList<>())
                .build();

        Club barcelona = Club.builder()
                .id(UUID.randomUUID())
                .name("FC Barcelona")
                .country("Spain")
                .titles(50)
                .players(new ArrayList<>())
                .build();

        Club chelmianka = Club.builder()
                .id(UUID.randomUUID())
                .name("SKS Chełmianka Gdańsk")
                .country("Poland")
                .titles(0)
                .players(new ArrayList<>())
                .build();



        Player vini = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Vinicius")
                .lastName("Junior")
                .goals(123)
                .country("Brasil")
                .club(realmadryt)
                .build();

        Player mbappe = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Kylian")
                .lastName("Mbappe")
                .goals(542)
                .country("France")
                .club(realmadryt)
                .build();

        Player bellingham = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Jude")
                .lastName("Bellingham")
                .country("England")
                .goals(94)
                .club(realmadryt)
                .build();



        Player lewandowski = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Robert")
                .lastName("Lewandowski")
                .country("Poland")
                .goals(650)
                .club(barcelona)
                .build();

        Player pedri = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Pedri")
                .lastName("Gonzalez")
                .country("Spain")
                .goals(34)
                .club(barcelona)
                .build();

        Player yamal = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Lamine")
                .lastName("Yamal")
                .country("Spain")
                .goals(54)
                .club(barcelona)
                .build();


        Player sobel = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Michał")
                .lastName("Matysiak")
                .country("Poland")
                .goals(0)
                .club(chelmianka)
                .build();

        Player prezes = Player.builder()
                .id(UUID.randomUUID())
                .firstName("Miłosz")
                .lastName("Sandach")
                .country("Poland")
                .goals(32)
                .club(chelmianka)
                .build();

        clubRepository.saveAll(List.of(realmadryt,barcelona,chelmianka));
        playerRepository.saveAll(List.of(vini,mbappe,bellingham,yamal,pedri,lewandowski,sobel,prezes));
    }


}
