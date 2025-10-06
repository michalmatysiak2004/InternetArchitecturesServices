package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "clubs" )
public class Club implements Comparable<Club>, Serializable {
    @Id
    @Column(name="club_id")
    UUID id;

    @Column(name = "name" )
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "titles")
    private int titles;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Player> players = new ArrayList<>();

    @PrePersist
    public void init(){
        if(id==null) this.id = UUID.randomUUID();
    }

    @Override
    public int compareTo(Club o) {
        int result = this.name.compareTo( o.getName());
        if(result != 0) return result;
        result = this.country.compareTo(o.getCountry());
        if (result != 0 ) return result;
        result = Integer.compare(this.titles, o.getTitles());
        return result;
    }

    @Override
    public String toString(){
        return  name + "  kraj: " + country + "  liczba tytułow: " + titles;
    }
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.setClub(this);
        }
    }
}
