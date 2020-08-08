package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UsersData createUser(UsersData users) throws UserAlreadyExistsException;
     UsersData getUser(String emailid, String password) throws UserDetailsNotfoundException;
    public UsersData getCustomerDetailsByEmail(String email) throws UserDetailsNotfoundException;
    public UserDetails loadCustomerDetails(String email) throws UserDetailsNotfoundException;
    UsersData getUserDetails(int id) throws UserDetailsNotfoundException;
    UsersData getCustomerDetailsByMobile(String mobileNo) throws UserDetailsNotfoundException;
}
