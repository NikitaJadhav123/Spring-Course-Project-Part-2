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
public class DTOEntityConverter {

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



    public UsersData convertToUserEntity(UserDto userDto) throws UserDetailsNotfoundException, UserAlreadyExistsException, BookingDetailsNotFoundException, RoleDetailsNotFoundException {
        UsersData usersData = new UsersData();
        usersData.setFirstName(userDto.getFirstName());
        usersData.setLastName(userDto.getLastName());
        usersData.setEmailId(userDto.getEmail());
        usersData.setMobileNo(userDto.getMobileNo());
        usersData.setPassword(userDto.getPassword());
        usersData.setId(userDto.getId());
        return usersData;
    }





    public Vehicle convertToVehicleEntity(VehicleDto vehicleDto) throws VehicleSubcategoryDetailsNotFoundException,FuelTypeNotFoundException,LocationNotFoundException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getVehicleid());
        vehicle.setAvailabilityStatus(vehicleDto.getAvailabilityStatus());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setImageUrl(vehicleDto.getCarImageUrl());
        vehicle.setModel(vehicleDto.getVehicleModel());
        vehicle.setNo(vehicleDto.getVehicleNumber());
        vehicle.setVehicleSub(vehicleSubCategoryService.getVehicleSubCategoryDetails(vehicleDto.getVehicleSubCategoryId()));
        vehicle.setFuelType(fuelTypeService.getFuelTypeDetails(vehicleDto.getFuelTypeId()));
        vehicle.setLocation(locationService.getLocationDetails(vehicleDto.getLocationId()));
        return vehicle;
    }




    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws VehicleSubcategoryDetailsNotFoundException, FuelTypeNotFoundException, LocationNotFoundException, BookingDetailsNotFoundException, UserDetailsNotfoundException, VehicleDetailsNotFoundException {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getBookingId());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setDropOffDate(bookingDTO.getDropoffDate());
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setAmount(bookingDTO.getAmount());
        booking.setLocation(locationService.getLocationDetails(bookingDTO.getLocationId()));
        booking.setUsers(userService.getUserDetails(bookingDTO.getUserId()));
        booking.setVehicle(vehicleService.getVehicleDetails(bookingDTO.getVehicleId()));

        return booking;
    }



}
