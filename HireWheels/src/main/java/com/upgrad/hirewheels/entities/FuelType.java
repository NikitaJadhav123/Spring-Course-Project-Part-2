package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   //	PRIMARY KEY

    @Column(nullable = false, unique = true)
    private String fuel;

    @OneToMany(mappedBy = "fuelType" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Vehicle> vehicle;

    public FuelType(String fuel) {
        this.fuel = fuel;
    }

    public FuelType(){

    }

}
