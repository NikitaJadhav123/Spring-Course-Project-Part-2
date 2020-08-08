package com.upgrad.hirewheels.controllers;

import com.upgrad.hirewheels.dtos.BookingDTO;
import com.upgrad.hirewheels.dtos.VehicleDto;
import com.upgrad.hirewheels.exceptions.APIException;
import com.upgrad.hirewheels.security.jwt.JwtTokenProvider;
import com.upgrad.hirewheels.service.UserService;
import com.upgrad.hirewheels.service.VehicleService;
import com.upgrad.hirewheels.utils.DTOEntityConverter;
import com.upgrad.hirewheels.utils.EntityDTOConverter;
import com.upgrad.hirewheels.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {

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


    @Autowired
    VehicleService vehicleService;


    @GetMapping(value="/vehicles")
    @ResponseBody
    public ResponseEntity GetVehicles(@RequestBody List<VehicleDto> vehicleDto, @RequestBody BookingDTO bookingDTO, @RequestParam ("categoryName") String categoryName, @RequestParam ("pickUpDate") String pickUpDate, @RequestParam ("dropDate") String dropDate, @RequestParam int locationId, @RequestHeader(value = "X-ACCESS-TOKEN") String accessToken ) throws APIException {
        System.out.println("get customer details controller");
        String email = jwtTokenProvider.getEmail(accessToken);
        if(email == null) {
            throw new APIException("Please add proper authentication");
        }
        List<VehicleDto> vehicleDtoList=new ArrayList<>();

        if(categoryName==null || pickUpDate==null || dropDate==null || locationId<=0 ) {

            return new ResponseEntity<>(vehicleService.getAllVehicles(),HttpStatus.OK);
        }



           for (VehicleDto vehicleDtos : vehicleDto) {
               if (vehicleDtos.getAvailabilityStatus() == 1) {
                   if (bookingDTO.getVehicleId() == vehicleDtos.getVehicleid() &&
                           bookingDTO.getPickupDate().equals(pickUpDate) &&
                           bookingDTO.getDropoffDate().equals(dropDate) &&
                           bookingDTO.getLocationId() == locationId) {
                       vehicleDtoList.add(vehicleDtos);
                   }
               }
           }


        return new ResponseEntity<>(vehicleDtoList,HttpStatus.OK);
    }
}
