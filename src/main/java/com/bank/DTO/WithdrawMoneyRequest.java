package com.bank.DTO;

import lombok.Data;

@Data
public class WithdrawMoneyRequest {
    private Long userId;
    private String userAccountType;
    private Long amount;
}
