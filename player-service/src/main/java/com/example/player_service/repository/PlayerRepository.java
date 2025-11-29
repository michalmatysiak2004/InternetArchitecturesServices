package com.example.player_service.repository;



import com.example.player_service.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    List<Player> findByClubID(UUID clubId);
    Optional<Player> findByFirstNameAndLastName(String firstname, String lastname);


}