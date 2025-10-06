package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@Builder
public class Club implements Comparable<Club>, Serializable {
    private String name;
    private String country;
    private int titles;
    private List<Player> players;


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
