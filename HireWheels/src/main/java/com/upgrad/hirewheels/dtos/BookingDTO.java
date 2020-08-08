package com.upgrad.hirewheels.dtos;

import lombok.Data;

@Data
public class BookingDTO {


    int bookingId;
    int userId;
    int vehicleId;
    String pickupDate;
    String dropoffDate;
    String bookingDate;
    int locationId;
    double amount;

}



