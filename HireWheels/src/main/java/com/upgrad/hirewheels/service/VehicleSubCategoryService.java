package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.VehicleSubcategory;
import com.upgrad.hirewheels.exceptions.VehicleSubcategoryDetailsNotFoundException;

public interface VehicleSubCategoryService {


    VehicleSubcategory getVehicleSubCategoryDetails(int id) throws VehicleSubcategoryDetailsNotFoundException;
}
