package com.upgrad.hirewheels.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Location")
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;	//PRIMARY KEY


    @Column( nullable = false)
    private String vehicleLocation;


    @Column( nullable = false)
    private String vehicleAddress;


    @Column( nullable = false)
    private String pinCode;


    @JsonBackReference
    @ManyToOne //FOREIGN KEY
    private City city;


    public Location(){

    }

    public Location(String vehicleLocation, String vehicleAddress, City city, String pincode) {
        this.vehicleLocation = vehicleLocation;
        this.vehicleAddress = vehicleLocation;
        this.pinCode = pincode;
        this.city = city;
    }

    @OneToMany(mappedBy = "location" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "location" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Vehicle> vehicle;


    public Location(String vehicleLocation, String vehicleAddress,String pincode) {
        this.vehicleLocation = vehicleLocation;
        this.vehicleAddress = vehicleAddress;
        this.pinCode = pincode;

    }
}
