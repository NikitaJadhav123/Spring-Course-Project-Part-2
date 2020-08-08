package com.upgrad.hirewheels.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;	//PRIMARY KEY


    @Column( nullable = false)
    private String model;


    @Column( nullable = false)
    private String no;

    @Column( nullable = false)
    private String color;


    @Column( precision=1, nullable = false)
    private int availabilityStatus;

    @Column( nullable =false)
    private String imageUrl;

    @OneToMany(mappedBy = "vehicle" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Booking> booking;

    @JsonBackReference
    @ManyToOne  //foreign key
    private UsersData users;

    @JsonBackReference
    @ManyToOne  //foreign key
    private Location location;

    @JsonBackReference
    @ManyToOne  //foreign key
    private FuelType fuelType;

    @JsonBackReference
    @ManyToOne //foreign key
    private VehicleSubcategory vehicleSub;


    public Vehicle(){

    }

    public Vehicle(String model, String no, String color, int availabilityStatus, String imageUrl) {
        this.model = model;
        this.no = no;
        this.color = color;
        this.availabilityStatus = availabilityStatus;
        this.imageUrl = imageUrl;
    }

    public Vehicle(String model, String no, String color, int availabilityStatus, String imageUrl, UsersData users, FuelType fuelType , Location location, VehicleSubcategory vehicleSub) {
        this.model = model;
        this.no = no;
        this.color = color;
        this.availabilityStatus = availabilityStatus;
        this.imageUrl = imageUrl;
        this.location = location;
        this.fuelType = fuelType;
        this.vehicleSub = vehicleSub;
      //  this.users=users;
    }
}
