package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dtos.ChangeAvailabilityDTO;
import com.upgrad.hirewheels.dtos.VehicleDto;
import com.upgrad.hirewheels.exceptions.APIException;
import com.upgrad.hirewheels.service.FuelTypeService;
import com.upgrad.hirewheels.service.LocationService;
import com.upgrad.hirewheels.service.VehicleSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleValidatorImpl implements VehicleValidator {

    @Autowired
    VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    FuelTypeService fuelTypeService;

    @Autowired
    LocationService locationService;


    public void validateVehicle(VehicleDto vehicleDto) throws APIException {

        if(vehicleDto.getColor() == null || vehicleDto.getColor().length() <= 0)
            throw new APIException("Invalid vehicle color");
        if(vehicleDto.getVehicleModel() == null || vehicleDto.getVehicleModel().length() <=0)
            throw new APIException("Invalid vehicle model");
        if(vehicleDto.getCarImageUrl() == null || vehicleDto.getCarImageUrl().length() <=0)
            throw new APIException("Invalid image url");
        if(vehicleDto.getVehicleNumber() == null || vehicleDto.getVehicleNumber().length() <=0)
            throw new APIException("Invalid  vehicle number");
        if(vehicleDto.getAvailabilityStatus() !=0 && vehicleDto.getAvailabilityStatus() !=1 )
            throw new APIException("Invalid status");
        if(vehicleDto.getLocationId() <= 0)
            throw new APIException("Invalid location");
        if(vehicleDto.getFuelTypeId() <= 0)
            throw new APIException("Invalid fuel type");
        if(vehicleDto.getVehicleSubCategoryId() <= 0)
            throw new APIException("Invalid vehicle subcategory type");
    }


    public void validateStatusVehicle(ChangeAvailabilityDTO changeAvailabilityDTO) throws APIException {

        if(changeAvailabilityDTO.getAvailability_status() !=0 && changeAvailabilityDTO.getAvailability_status() !=1 )
            throw new APIException("Invalid status");

    }
}
