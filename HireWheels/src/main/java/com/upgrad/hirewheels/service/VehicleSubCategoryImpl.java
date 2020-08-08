package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.VehicleSubcategoryDAO;
import com.upgrad.hirewheels.entities.VehicleSubcategory;
import com.upgrad.hirewheels.exceptions.VehicleSubcategoryDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VehicleSubcategoryService")
public class VehicleSubCategoryImpl implements VehicleSubCategoryService{


    @Autowired
    @Qualifier("vehicleSubcategoryDAO")
    private VehicleSubcategoryDAO vehicleSubcategoryDAO;

    @Override
    public VehicleSubcategory getVehicleSubCategoryDetails(int id) throws VehicleSubcategoryDetailsNotFoundException {
         VehicleSubcategory vehicleSubcategory = vehicleSubcategoryDAO.findById(id).orElseThrow(
                ()->  new VehicleSubcategoryDetailsNotFoundException("VehicleSubcategory not found for " + id));
        return vehicleSubcategory;
    }
}
