package com.bank.DTO;

import lombok.Data;

@Data
public class DepositeMoneyRequest {
    private Long userId;
    private String userAccountType;
    private Long amount;
}
