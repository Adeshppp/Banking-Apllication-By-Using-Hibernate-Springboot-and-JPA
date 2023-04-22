package com.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message){
        super(message);
    }
}

