package com.upgrad.hirewheels.exceptions;

public class BookingAmountException extends Exception {

    public BookingAmountException() {
        super();
    }

    public BookingAmountException(String message) {
        super(message);
    }

    public BookingAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingAmountException(Throwable cause) {
        super(cause);
    }

    protected BookingAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
