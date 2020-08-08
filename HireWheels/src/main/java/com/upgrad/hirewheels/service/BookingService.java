package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.BookingAmountException;
import com.upgrad.hirewheels.exceptions.BookingDetailsNotFoundException;

public interface BookingService {


    Booking addBooking(Booking booking) throws BookingAmountException;
    Booking getBookingDetails(int id) throws BookingDetailsNotFoundException;
}
