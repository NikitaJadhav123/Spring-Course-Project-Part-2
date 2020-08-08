package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.Vehicle;

public interface AdminService {



     Vehicle registerVehicle(Vehicle vehicle);
     Vehicle changeAvailability(Vehicle vehicle);

}
