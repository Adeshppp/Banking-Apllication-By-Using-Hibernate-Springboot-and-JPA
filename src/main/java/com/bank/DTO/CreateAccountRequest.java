package com.bank.DTO;

import lombok.Data;

@Data
public class CreateAccountRequest{
    private Long userId;
    private String accountType;
}
