package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import com.upgrad.hirewheels.exceptions.VehicleDetailsNotFoundException;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    public Vehicle updateVehicleStatus(int initialVehicleId, Vehicle vehicle) throws VehicleDetailsNotFoundException;

    public Vehicle getVehicleDetails(int id) throws VehicleDetailsNotFoundException;

     List<Vehicle> getVehicleByUserID(int id) throws UserDetailsNotfoundException;

    public Vehicle acceptVehicleDetails(Vehicle vehicle);

   // public List<Vehicle> getAvailableVehicles(VehicleCategory vehicleCategory);
}
