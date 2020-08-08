package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.dtos.BookingDTO;
import com.upgrad.hirewheels.exceptions.APIException;
import com.upgrad.hirewheels.exceptions.UserDetailsNotfoundException;
import com.upgrad.hirewheels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingValidatorImpl implements BookingValidator {

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    UserService userService;




    public void validateBooking(BookingDTO bookingDto) throws APIException , UserDetailsNotfoundException{

        if(bookingDto.getDropoffDate() == null || bookingDto.getDropoffDate().length() <= 0)
            throw new APIException("Invalid drop off");
        if(bookingDto.getBookingDate() == null || bookingDto.getBookingDate().length() <=0)
            throw new APIException("Invalid booking date");
        if(bookingDto.getPickupDate() == null || bookingDto.getPickupDate().length() <=0)
            throw new APIException("Invalid pickup date");
        if (bookingDto.getUserId()>userService.getUserDetails(bookingDto.getUserId()).getWalletMoney())
                throw new APIException("Invalid  amount");
        if(bookingDto.getLocationId() <= 0)
            throw new APIException("Invalid location");
        if(bookingDto.getUserId() <= 0)
            throw new APIException("Invalid user");
    }
}
