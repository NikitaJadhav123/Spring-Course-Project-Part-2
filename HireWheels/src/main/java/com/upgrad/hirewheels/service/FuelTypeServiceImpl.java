package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.FuelTypeDAO;
import com.upgrad.hirewheels.entities.FuelType;
import com.upgrad.hirewheels.exceptions.FuelTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("FuelTypeService")
public class FuelTypeServiceImpl implements FuelTypeService {


    @Autowired
    @Qualifier("fuelTypeDAO")
    private FuelTypeDAO fuelTypeDAO;

    @Override
    public FuelType getFuelTypeDetails(int id) throws FuelTypeNotFoundException {
        FuelType fuelType =fuelTypeDAO.findById(id).orElseThrow(
                ()->  new FuelTypeNotFoundException("FuelType not found for " + id));
        return fuelType;
    }
}
