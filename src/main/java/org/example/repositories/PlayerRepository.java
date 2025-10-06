package org.example.repositories;

import org.example.entities.Club;
import org.example.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    List<Player> findByClub(Club club);
}
