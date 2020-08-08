package com.upgrad.hirewheels.exceptions;

public class VehicleSubcategoryDetailsNotFoundException extends Exception {


    public VehicleSubcategoryDetailsNotFoundException() {
    }

    public VehicleSubcategoryDetailsNotFoundException(String message) {
        super(message);
    }

    public VehicleSubcategoryDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleSubcategoryDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleSubcategoryDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
