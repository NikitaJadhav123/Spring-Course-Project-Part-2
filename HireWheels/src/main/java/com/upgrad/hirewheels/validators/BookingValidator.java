package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dtos.BookingDTO;
import com.upgrad.hirewheels.exceptions.APIException;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;

public interface BookingValidator {
   void validateBooking(BookingDTO bookingDto) throws APIException, UserDetailsNotfoundException;
}
