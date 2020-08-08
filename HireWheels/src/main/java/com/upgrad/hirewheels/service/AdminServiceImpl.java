package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.VehicleDAO;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {


    @Autowired
    @Qualifier("vehicleDAO")
    private VehicleDAO vehicleDAO;


    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
       return vehicleDAO.save(vehicle);

    }

    @Override
    public Vehicle changeAvailability(Vehicle vehicle) {
        if(vehicle.getAvailabilityStatus()==0){
            vehicle.setAvailabilityStatus(1);
        }
        else{
            vehicle.setAvailabilityStatus(0);
        }
        return vehicle;
    }



}
