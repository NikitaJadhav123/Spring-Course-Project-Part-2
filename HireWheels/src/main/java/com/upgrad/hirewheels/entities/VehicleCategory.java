package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class VehicleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   //	PRIMARY KEY

    @Column( nullable = false, unique = true)
    private String vname;

    @OneToMany(mappedBy = "vehicleCat" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<VehicleSubcategory> vehicleSub;

    public VehicleCategory(String vname) {
        this.vname = vname;
    }

    public VehicleCategory(){

    }
}
