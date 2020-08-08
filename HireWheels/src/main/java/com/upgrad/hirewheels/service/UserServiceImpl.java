package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.RoleDAO;
import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.exceptions.UserAlreadyExistsException;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("UserService")
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("usersDAO")
    private UsersDAO usersDAO;
    @Autowired
    RoleDAO roleDAO;



    @Override
    public UsersData getUserDetails(int id) throws UserDetailsNotfoundException {
        UsersData user =usersDAO.findById(id).orElseThrow(
                ()->  new UserDetailsNotfoundException("User not found for " + id));
        return user;
    }



    @Override
        public UsersData createUser(UsersData users) throws UserAlreadyExistsException {


            if(usersDAO.findByEmailId(users.getEmailId()).isPresent()) {
                throw new UserAlreadyExistsException("Email Already Exists");
            }

            if(usersDAO.findByMobileNo(users.getMobileNo()).isPresent()) {
                throw new UserAlreadyExistsException("Mobile Number Already Exists");
            }

            return usersDAO.save(users);
        }

    @Override
    public UsersData getUser(String emailid,String password) throws UserDetailsNotfoundException {
            
            if(!usersDAO.findByEmailId(emailid).isPresent()) {
                throw new UserDetailsNotfoundException("User not Registered");
            }
            
            if(!usersDAO.findByPassword(password).isPresent()){
                throw new UserDetailsNotfoundException("Unauthorized User");
            }

        return usersDAO.findByEmailId(emailid).get();
    }

    public UsersData getCustomerDetailsByEmail(String email) throws UserDetailsNotfoundException {
        UsersData customer = usersDAO.findByEmailId(email).orElseThrow(
                ()->  new UserDetailsNotfoundException("User not found for email" + email));
        return customer;
    }

    public UsersData getCustomerDetailsByMobile(String mobileNo) throws UserDetailsNotfoundException {
        UsersData customer = usersDAO.findByMobileNo(mobileNo).orElseThrow(
                ()->  new UserDetailsNotfoundException("User not found for mobile" + mobileNo));
        return customer;
    }



    @Override
    public UserDetails loadCustomerDetails(String email) throws UserDetailsNotfoundException {
        UsersData user = usersDAO.findByEmailId(email).orElseThrow(
                ()->  new UserDetailsNotfoundException("User not found for " + email));

        return  new User(user.getEmailId(), user.getPassword() , new ArrayList<>());

    }


}
