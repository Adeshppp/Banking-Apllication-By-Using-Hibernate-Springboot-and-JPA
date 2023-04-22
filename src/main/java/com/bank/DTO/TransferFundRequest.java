package com.bank.DTO;

import lombok.Data;

@Data
public class TransferFundRequest {
    private Long fromUserId;
    private String fromAccountType;
    private Long toUserId;
    private String toAccountType;
    private Long amount;

}
