package com.upgrad.hirewheels.controllers;


import com.upgrad.hirewheels.dtos.LoginDTO;
import com.upgrad.hirewheels.dtos.UserDto;
import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.responses.CustomResponse;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.service.UserService;
import com.upgrad.hirewheels.utils.DTOEntityConverter;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import com.upgrad.hirewheels.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;
    @Autowired
    UserValidator userValidator;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;


    @PostMapping( value = "/users")
    @ResponseBody
    public ResponseEntity signUp(@RequestBody UserDto customerDTO) throws UserAlreadyExistsException, APIException, UserDetailsNotfoundException, BookingDetailsNotFoundException,RoleDetailsNotFoundException{
        System.out.println("entered sign up");
        try{
            UsersData customer = userService.getCustomerDetailsByEmail(customerDTO.getEmail());
            if(customer != null)
                throw new UserAlreadyExistsException("Customer email already exists : " + customerDTO.getEmail());
        }catch (UserDetailsNotfoundException ex){
            System.out.println("User does not exist for the given details");
        }
        try{
            UsersData customer = userService.getCustomerDetailsByMobile(customerDTO.getMobileNo());
            if(customer != null)
                throw new UserAlreadyExistsException("Customer mobile no already exists : " + customerDTO.getMobileNo());
        }catch (UserDetailsNotfoundException ex){
            System.out.println("User does not exist for the given details");
        }
        try {
            ResponseEntity responseEntity ;
            String firstName=customerDTO.getFirstName();
            String email = customerDTO.getEmail();
            String password = customerDTO.getPassword();
            String mobileNo= customerDTO.getMobileNo();
            if(StringUtils.isEmpty(firstName)){
                    CustomResponse response = new CustomResponse(LocalDateTime.now(), "FirstName cannot be null or empty", 400);
                    responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    return responseEntity;
            }
            if(StringUtils.isEmpty(password) || password.length()<5){
                CustomResponse response = new CustomResponse(LocalDateTime.now(), "Password cannot be null or less than 5 characters", 400);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            if(StringUtils.isEmpty(mobileNo) || mobileNo.length()!=10){
                CustomResponse response = new CustomResponse(LocalDateTime.now(), "Mobile Number cannot be null or empty and must be 10 digits", 400);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            if(StringUtils.isEmpty(email) ){
                CustomResponse response = new CustomResponse(LocalDateTime.now(), "EmailId can not be null", 400);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;

            }
            userValidator.validateUser(customerDTO);
            String token = jwtTokenProvider.createToken(email);
            UsersData newCustomer = dtoEntityConverter.convertToUserEntity(customerDTO);
            UsersData savedCustomer = userService.createUser(newCustomer);
            UserDto savedCustomerDTO = entityDTOConverter.convertToUserDTO(savedCustomer);
            savedCustomerDTO.setJwtToken(token);
            CustomResponse response = new CustomResponse(LocalDateTime.now(), "User Successfully Signed Up", 200);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            return responseEntity;
        } catch (UserAlreadyExistsException e) {

            throw new UserAlreadyExistsException("Email " + customerDTO.getEmail() + " already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @PostMapping( value = "/access-tokens")
    @ResponseBody
    public ResponseEntity signIn(@RequestBody LoginDTO loginDTO) throws APIException, UserDetailsNotfoundException, BadCredentialsException, CustomException {
        System.out.println("Print statement here _____________________________");
        try {
            userValidator.validateuserLogin(loginDTO);
            Map<String, String> model = new HashMap<>();
            String email = loginDTO.getEmail();
            String password = loginDTO.getPassword();
            UsersData savedCustomer = userService.getCustomerDetailsByEmail(email);
            if(StringUtils.isEmpty(email) ||  StringUtils.isEmpty(password)){
                model.put("Error", "Email id is invalid/ Password is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
            }

            if(!savedCustomer.getEmailId().equals(email)){
                throw new BadCredentialsException("User Not Registered");
            }
            if(!savedCustomer.getPassword().equals(password)){
                throw new BadCredentialsException("Invalid Credentials");
            }
            ResponseEntity responseEntity;
            String token = jwtTokenProvider.createToken(email);
            UserDto savedCustomerDTO = entityDTOConverter.convertToUserDTO(savedCustomer);
            savedCustomerDTO.setJwtToken(token);
             return ResponseEntity.status(HttpStatus.OK).body(savedCustomerDTO);
        } catch (Exception e) {
            throw new CustomException("Email " + loginDTO.getEmail() + "not registered/password incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



}
