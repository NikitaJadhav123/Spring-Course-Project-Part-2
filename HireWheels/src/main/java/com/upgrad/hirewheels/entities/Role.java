package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity

public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   //	PRIMARY KEY

    @Column( nullable = false, unique = true)
    private String role;

    @OneToMany(mappedBy = "role" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<UsersData> users;



    public Role() {

    }

    public Role(String roleName) {
        this.role = roleName;
    }

    public Role(int roleId, String roleName) {
        this.id = roleId;
        this.role= roleName;
    }





}
