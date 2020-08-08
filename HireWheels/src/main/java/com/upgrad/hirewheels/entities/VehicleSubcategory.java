package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class VehicleSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;	//PRIMARY KEY

    @Column( nullable = false,unique =true)
    private String vsname;

    @Column(precision=10, scale=2)
    private  double pricePerHour;

    public VehicleSubcategory(String vsname, double pricePerHour, VehicleCategory vehicleCat) {
        this.vsname = vsname;
        this.pricePerHour = pricePerHour;
        this.vehicleCat = vehicleCat;
    }

    public VehicleSubcategory(String vsname, double pricePerHour) {
        this.vsname = vsname;
        this.pricePerHour = pricePerHour;
    }

    public VehicleSubcategory(){

    }

    @ManyToOne  //foreign key
    private VehicleCategory vehicleCat;

    @OneToMany(mappedBy = "vehicleSub" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Vehicle> vehicle;


}
