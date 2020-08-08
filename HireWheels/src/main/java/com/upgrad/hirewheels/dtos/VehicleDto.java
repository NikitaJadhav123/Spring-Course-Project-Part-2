package com.upgrad.hirewheels.dtos;

import lombok.Data;

@Data
public class VehicleDto {

    private int vehicleid;


    private String vehicleModel;

    private String vehicleNumber;

    int vehicleSubCategoryId;

    private String color;

    int  fuelTypeId;

    int  locationId;

    private String carImageUrl;

    private int availabilityStatus;







}
