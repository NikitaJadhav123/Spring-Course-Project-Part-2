package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dtos.ChangeAvailabilityDTO;
import com.upgrad.hirewheels.dtos.VehicleDto;
import com.upgrad.hirewheels.exceptions.APIException;

public interface VehicleValidator {
    void validateVehicle(VehicleDto vehicleDto) throws APIException;
    void validateStatusVehicle(ChangeAvailabilityDTO changeAvailabilityDTO) throws APIException;
}
