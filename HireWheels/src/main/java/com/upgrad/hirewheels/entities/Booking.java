package com.upgrad.hirewheels.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;	//PRIMARY KEY


    @Column(nullable = false)
    private String pickupDate;


    @Column( nullable = false)
    private  String dropOffDate;

    @Column( nullable = false)
    private String bookingDate;

    @Column(precision=10, scale=2,nullable = false)
    private double amount;

    @JsonBackReference
    @ManyToOne   //foreign key
    private UsersData users;

    @JsonBackReference
    @ManyToOne   //foreign key
    private Location location;

    @JsonBackReference
    @ManyToOne  //foreign key
    private Vehicle vehicle;

    public Booking(String pickupDate, String dropOffDate, String bookingDate, double amount) {
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.bookingDate = bookingDate;
        this.amount = amount;
    }

    public Booking(String pickupDate, String dropOffDate, String bookingDate, double amount, UsersData users) {
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.bookingDate = bookingDate;
        this.amount = amount;
        this.users = users;
    }

    public Booking(String pickupDate, String dropOffDate, String bookingDate, double amount, UsersData users, Location location, Vehicle vehicle) {
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.bookingDate = bookingDate;
        this.amount = amount;
        this.users = users;
        this.location = location;
        this.vehicle = vehicle;
    }

    public Booking(){

    }
}
