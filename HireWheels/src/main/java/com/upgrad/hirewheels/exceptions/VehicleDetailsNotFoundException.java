package com.upgrad.hirewheels.exceptions;

public class VehicleDetailsNotFoundException extends Exception {

    public VehicleDetailsNotFoundException() {
    }

    public VehicleDetailsNotFoundException(String message) {
        super(message);
    }

    public VehicleDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
