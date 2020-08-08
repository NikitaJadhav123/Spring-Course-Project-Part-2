package com.upgrad.hirewheels.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
// combine  all below things add @Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode

// use @Transactional no need of begin and commit

@Entity
public class UsersData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;   //	PRIMARY KEY  // min,max validation automatically

    @Column(nullable = false)
    private String firstName;

    @Column //do not mention it varchar.char
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String  emailId;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Column(precision=10, scale=2)
    private double walletMoney;


    @JsonBackReference
   @ManyToOne(fetch = FetchType.EAGER) //foreign key
   private Role role;

    @OneToMany(mappedBy = "users" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;



    @OneToMany(mappedBy = "users" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   private List<Booking> bookings;

    public UsersData(){

    }

    public UsersData(String firstName, String lastName, String password, String email, String mobileNo, double walletMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = email;
        this.mobileNo = mobileNo;
        this.walletMoney = walletMoney;
    }

    public UsersData(String firstName, String lastName, String password, String email, String mobileNo, double walletMoney,Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = email;
        this.mobileNo = mobileNo;
        this.walletMoney = walletMoney;
        this.role=role;
    }



    @Override
    public String toString() {
        return "Users{" +
                "userId='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", walletMoney=" + walletMoney +
                '}';
    }
}
