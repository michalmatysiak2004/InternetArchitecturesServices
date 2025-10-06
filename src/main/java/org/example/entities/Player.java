package org.example.entities;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name= "players")
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Comparable<Player>, Serializable {

    @Id
    @Column(name="player_id")
    UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="country")
    private String country;

    @Column(name="goals")
    private int goals;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    Club club;



    @Override // natural ordering lastname-> firstname-> goals
    public int compareTo(Player o) {
        int result = this.lastName.compareTo(o.getLastName());
        if(result != 0) return result;
        result = this.firstName.compareTo(o.getFirstName());
        if(result != 0) return 0;
        result = Integer.compare(this.goals, o.getGoals());
        return result;
    }
    @Override
    public String toString(){
        return firstName+ " " + lastName + "  gole: " + goals + " klub: " + club.getName();
    }
}
