package com.upgrad.hirewheels.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomResponse {
    private LocalDateTime timestamp;
    private String errorMessage;
    private int statusCode;

    public CustomResponse(){

    }

    public CustomResponse(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public CustomResponse(LocalDateTime timestamp, String errorMessage, int statusCode) {
        this.timestamp=timestamp;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }


}
