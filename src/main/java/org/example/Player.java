package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Player implements Comparable<Player>, Serializable {
    private String firstName;
    private String lastName;
    private String country;
    private int goals;
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
