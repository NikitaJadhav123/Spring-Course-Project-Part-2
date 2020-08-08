package com.upgrad.hirewheels.utils;

import com.upgrad.hirewheels.dtos.BookingDTO;
import com.upgrad.hirewheels.dtos.UserDto;
import com.upgrad.hirewheels.dtos.VehicleDto;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.*;
import com.upgrad.hirewheels.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOConverter {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    BookingService bookingService;
    @Autowired
    RoleService roleService;


    @Autowired
    VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    FuelTypeService fuelTypeService;

    @Autowired
    LocationService locationService;




    public UserDto convertToUserDTO(UsersData customer){
        UserDto customerDTO = new UserDto();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setEmail(customer.getEmailId());
        customerDTO.setId(customer.getId());

        return customerDTO;
    }

    public VehicleDto convertToVehicleDTO(Vehicle vehicle) throws VehicleSubcategoryDetailsNotFoundException, FuelTypeNotFoundException, LocationNotFoundException {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleid(vehicle.getId());
        vehicleDto.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        vehicleDto.setColor(vehicle.getColor());
        vehicleDto.setCarImageUrl(vehicle.getImageUrl());
        vehicleDto.setVehicleModel(vehicle.getModel());
        vehicleDto.setVehicleNumber(vehicle.getNo());
        vehicleDto.setVehicleSubCategoryId(vehicle.getVehicleSub().getId());
        vehicleDto.setFuelTypeId(vehicle.getFuelType().getId());
        vehicleDto.setLocationId(vehicle.getLocation().getId());
        return vehicleDto;
    }



    public BookingDTO convertToBookingDTO(Booking booking) throws VehicleSubcategoryDetailsNotFoundException, FuelTypeNotFoundException, LocationNotFoundException, BookingDetailsNotFoundException, UserDetailsNotfoundException, VehicleDetailsNotFoundException {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setDropoffDate(booking.getDropOffDate());
        bookingDTO.setPickupDate(booking.getPickupDate());
        bookingDTO.setAmount(booking.getAmount());
        bookingDTO.setLocationId(booking.getLocation().getId());
        bookingDTO.setUserId(booking.getUsers().getId());
        bookingDTO.setVehicleId(booking.getVehicle().getId());

        return bookingDTO;
    }


}
