package com.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ZeroAmountException  extends RuntimeException {
    public ZeroAmountException() {
        super("Amount should be grater than 0.");
    }
}


