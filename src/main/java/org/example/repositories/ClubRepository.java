package org.example.repositories;

import org.example.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {

}
