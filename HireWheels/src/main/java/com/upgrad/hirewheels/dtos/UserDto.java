package com.upgrad.hirewheels.dtos;

import lombok.Data;

@Data
public class UserDto {

    private int id;   //	PRIMARY KEY  // min,max validation automatically

    private String firstName;

    private String lastName;

    private String password;

    private String  email;

    private String mobileNo;

    String jwtToken;


}
