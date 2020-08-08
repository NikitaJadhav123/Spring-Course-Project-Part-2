package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.FuelType;
import com.upgrad.hirewheels.exceptions.FuelTypeNotFoundException;

public interface FuelTypeService {


    FuelType getFuelTypeDetails(int id) throws FuelTypeNotFoundException;
}
