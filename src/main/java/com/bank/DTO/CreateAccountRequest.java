package com.bank.DTO;

import com.bank.entity.User;
import lombok.Data;

@Data
public class CreateAccountRequest{
    private User user;
    private String accountType;
}
