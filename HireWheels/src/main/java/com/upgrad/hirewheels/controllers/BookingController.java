package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dtos.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.responses.CustomResponse;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.service.BookingService;
import com.upgrad.hirewheels.service.UserService;
import com.upgrad.hirewheels.utils.DTOEntityConverter;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import com.upgrad.hirewheels.validators.BookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;

@Controller
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    BookingValidator bookingValidator;

    @Autowired
    BookingService bookingService;



    @PostMapping(value="/bookings",headers="Accept=application/json")
    public ResponseEntity addBooking(@RequestBody BookingDTO bookingDTO , @RequestHeader(value = "X-ACCESS-TOKEN") String accessToken) throws LocationNotFoundException, FuelTypeNotFoundException, VehicleSubcategoryDetailsNotFoundException, UserDetailsNotfoundException, APIException, BadCredentialsException, BookingDetailsNotFoundException, VehicleDetailsNotFoundException, BookingAmountException {
        String email = jwtTokenProvider.getEmail(accessToken);
        if(email == null)
            throw new APIException("Please add proper authentication");
        ResponseEntity responseEntity;
        if (bookingDTO.getUserId()>userService.getUserDetails(bookingDTO.getUserId()).getWalletMoney()){
            CustomResponse response = new CustomResponse(LocalDateTime.now(), "Insufficient balance. Please check with Admin", 400);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

            return responseEntity;
        }
        bookingValidator.validateBooking(bookingDTO);
        Booking newBooking = dtoEntityConverter.convertToBookingEntity(bookingDTO);
        Booking savedBooking = bookingService.addBooking(newBooking);
        BookingDTO savedBookingDTO = entityDTOConverter.convertToBookingDTO(savedBooking);
        CustomResponse response = new CustomResponse(LocalDateTime.now(), "Vehicle Added Successfully", 200);
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug("Accept new vehicle details",responseEntity);

        return responseEntity;
    }




}
