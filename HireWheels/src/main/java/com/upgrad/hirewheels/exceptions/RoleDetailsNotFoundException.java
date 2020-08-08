package com.upgrad.hirewheels.exceptions;

public class RoleDetailsNotFoundException extends Exception {
    public RoleDetailsNotFoundException() {
    }

    public RoleDetailsNotFoundException(String message) {
        super(message);
    }

    public RoleDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public RoleDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
