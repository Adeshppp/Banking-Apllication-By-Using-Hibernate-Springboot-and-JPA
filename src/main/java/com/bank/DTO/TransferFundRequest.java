package com.bank.DTO;

import lombok.Data;

@Data
public class TransferFundRequest {
    private Long fromAccountId;
    private String fromAccountType;
    private Long toAccountId;
    private String toAccountType;
    private Long amount;

}
