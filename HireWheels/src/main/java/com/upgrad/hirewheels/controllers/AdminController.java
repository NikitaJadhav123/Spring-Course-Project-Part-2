package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dtos.ChangeAvailabilityDTO;
import com.upgrad.hirewheels.dtos.VehicleDto;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.responses.CustomResponse;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.service.UserService;
import com.upgrad.hirewheels.service.VehicleService;
import com.upgrad.hirewheels.utils.DTOEntityConverter;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import com.upgrad.hirewheels.validators.VehicleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class AdminController {

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
    VehicleService vehicleService;

    @Autowired
    VehicleValidator vehicleValidator;


    @PostMapping(value="/vehicles",headers="Accept=application/json")
    public ResponseEntity addVehicle(@RequestBody VehicleDto vehicleDto , @RequestHeader(value = "X-ACCESS-TOKEN") String accessToken) throws LocationNotFoundException,FuelTypeNotFoundException,VehicleSubcategoryDetailsNotFoundException,UserDetailsNotfoundException, APIException, BadCredentialsException {
        String email = jwtTokenProvider.getEmail(accessToken);
        if(email == null)
            throw new APIException("Please add proper authentication");
        if(!userService.getCustomerDetailsByEmail(email).getRole().getRole().equalsIgnoreCase("Admin"))
            throw new BadCredentialsException("This feature is only available to admin");
        ResponseEntity responseEntity;

        try {
            vehicleValidator.validateVehicle(vehicleDto);
        }
        catch(APIException e){
            CustomResponse response = new CustomResponse(LocalDateTime.now(), "Fields shouldn’t be null or empty", 400);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        if(vehicleDto.getLocationId()<=0){
            CustomResponse response = new CustomResponse(LocalDateTime.now(), "Invalid Location Id for vehicle", 400);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
            Vehicle newVehicle = dtoEntityConverter.convertToVehicleEntity(vehicleDto);
            Vehicle savedVehicle = vehicleService.acceptVehicleDetails(newVehicle);
            VehicleDto savedVehicleDTO = entityDTOConverter.convertToVehicleDTO(savedVehicle);
            CustomResponse response = new CustomResponse(LocalDateTime.now(), "Vehicle Added Successfully", 200);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            logger.debug("Accept new vehicle details",responseEntity);

            return responseEntity;

    }


    @PutMapping(value = "/vehicles/{vehicleid}",headers="Accept=application/json")
    public ResponseEntity changeVehicleAvailability(@PathVariable(name = "vehicleid") int id , @RequestBody ChangeAvailabilityDTO changeAvailabilityDTO , @RequestHeader(value = "X-Access-Token") String accessToken) throws UserDetailsNotfoundException,APIException,BadCredentialsException,VehicleDetailsNotFoundException,VehicleSubcategoryDetailsNotFoundException,FuelTypeNotFoundException,LocationNotFoundException {
        String email = jwtTokenProvider.getEmail(accessToken);
        if(email == null)
            throw new APIException("Please add proper authentication");
        if(!userService.getCustomerDetailsByEmail(email).getRole().getRole().equalsIgnoreCase("Admin"))
            throw new BadCredentialsException("This feature is only available to admin");
        ResponseEntity responseEntity;
        try {
            vehicleValidator.validateStatusVehicle(changeAvailabilityDTO);
        }
        catch(APIException e){
            CustomResponse response = new CustomResponse(LocalDateTime.now(), " Invalid status value ", 400);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        Vehicle vehicle = vehicleService.getVehicleDetails(id);
        vehicle.setAvailabilityStatus(changeAvailabilityDTO.getAvailability_status());
        VehicleDto vehicleDto1=entityDTOConverter.convertToVehicleDTO(vehicle);
        CustomResponse response = new CustomResponse(LocalDateTime.now(), "Activity performed successfully", 200);
         responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug("Update vehicle details",responseEntity);

        return responseEntity;


    }



}
