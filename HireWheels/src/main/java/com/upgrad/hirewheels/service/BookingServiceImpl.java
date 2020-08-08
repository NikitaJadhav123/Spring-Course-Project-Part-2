package com.upgrad.hirewheels.service;

import com.upgrad.hirewheels.dao.BookingDAO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.BookingAmountException;
import com.upgrad.hirewheels.exceptions.BookingDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookingService")
public class BookingServiceImpl implements BookingService {


    @Autowired
    @Qualifier("bookingDAO")
   private BookingDAO bookingDAO;



    @Override
    public Booking addBooking(Booking booking) throws BookingAmountException {
        if(booking.getAmount()>booking.getUsers().getWalletMoney()){
            throw new BookingAmountException("Insufficient Balance. Please Check With Admin");
        }
        booking.setAmount(booking.getAmount());
       return bookingDAO.save(booking);
    }

    public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException {
        return bookingDAO.findById(id).orElseThrow(
                ()->  new BookingDetailsNotFoundException("Details not found for id : " + id));
    }
}
