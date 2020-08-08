package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.dao.VehicleDAO;
import com.upgrad.hirewheels.dao.VehicleSubcategoryDAO;
import com.upgrad.hirewheels.entities.UsersData;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import com.upgrad.hirewheels.exceptions.VehicleDetailsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("VehicleService")
public class VehicleServiceImpl implements VehicleService {


    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);



    @Autowired
    @Qualifier("vehicleDAO")
   private VehicleDAO vehicleDAO;

    @Autowired
    @Qualifier("usersDAO")
    private UsersDAO usersDAO;

    @Autowired
    @Qualifier("vehicleSubcategoryDAO")
    VehicleSubcategoryDAO vehicleSubcategoryDAO;


    @Override
    public Vehicle acceptVehicleDetails(Vehicle vehicle){
        return vehicleDAO.save(vehicle);
    }


    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    @Override
    public Vehicle getVehicleDetails(int id) throws VehicleDetailsNotFoundException {
        logger.debug("get vehicle details" , id);
        System.out.println("Get vehicle details ");
        Vehicle vehicle = vehicleDAO.findById(id).orElseThrow(
                ()->  new VehicleDetailsNotFoundException("vehicle not found for id" + id));
        return vehicle;
    }


    @Override
    public List<Vehicle> getVehicleByUserID(int id) throws UserDetailsNotfoundException {
        Optional<UsersData> optionalUsers = usersDAO.findById(id);
        UsersData foundUser = optionalUsers.orElseThrow(()->new UserDetailsNotfoundException("User Not Found "));


        return foundUser.getVehicles();
    }

    @Override
    public Vehicle updateVehicleStatus(int initialVehicleId, Vehicle vehicle) throws VehicleDetailsNotFoundException {
        Vehicle vehicle1=getVehicleDetails(initialVehicleId);
        vehicle1.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        return vehicle1;

    }

   // @Override
   // public List<Vehicle> getAvailableVehicles(VehicleCategory vehicleCategory) {
   //VehicleSubcategory vehicleSubcategory=new VehicleSubcategory();
    //    VehicleSubcategory vehicleSubcategory1=vehicleSubcategoryDAO.findById(vehicleCategory.getId()).get();

    //}
}
