package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   //	PRIMARY KEY

    @Column(nullable = false, unique = true)
    private String city;

    @OneToMany(mappedBy = "city" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Location> location;

    public City(String city) {
        this.city = city;
    }

    public City(){

    }
}
