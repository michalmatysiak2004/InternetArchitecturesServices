package com.example.club_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyDiscriminator;
import org.springframework.beans.factory.annotation.Value;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.io.Serializable;

import java.util.UUID;

@Setter
@Getter
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "clubs" )
public class Club implements Comparable<Club>, Serializable {
    @Id
    @Column(name="club_id")
    UUID id;

    @Column(name = "name" )
    private String name;

    @Column(name = "country")
    private String country;

    @Min(value=0)
    @Column(name = "titles")
    private int titles;

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

}