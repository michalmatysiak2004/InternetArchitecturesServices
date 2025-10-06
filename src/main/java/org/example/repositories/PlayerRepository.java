package org.example.repositories;

import org.example.entities.Club;
import org.example.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Club club(Club club);
    List<Player> findByClub(Club club);

    Optional<Player> findByFirstNameAndLastName(String firstname, String lastname);

}
