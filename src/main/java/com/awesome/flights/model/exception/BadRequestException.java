package com.awesome.flights.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    String message;
    public  BadRequestException(){
        super("Bad Request");

    }

    public  BadRequestException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getLocalizedMessage() {
        return this.message;
    }
}
