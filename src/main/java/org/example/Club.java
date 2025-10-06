package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "clubs" )
public class Club implements Comparable<Club>, Serializable {
    @Column(name = "name" )
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "titles")
    private int titles;
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Player> players = new ArrayList<>();


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
