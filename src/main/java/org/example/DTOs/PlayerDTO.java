package org.example.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerDTO implements Comparable<PlayerDTO> {
    private String firstName;
    private String lastName;
    private String country;
    private int goals;
    private String clubName;

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Goals: " + goals + " | Club: " + clubName;
    }

    @Override
    public int compareTo(PlayerDTO o) {
        int result = this.lastName.compareTo(o.getLastName());
        if (result != 0) return result;
        result = this.firstName.compareTo(o.getFirstName());
        if (result != 0) return result;
        return Integer.compare(this.goals, o.getGoals());
    }
}