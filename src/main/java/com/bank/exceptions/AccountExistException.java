package com.bank.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class AccountExistException extends RuntimeException{
    public AccountExistException(String message) {
        super(message);
    }
}
