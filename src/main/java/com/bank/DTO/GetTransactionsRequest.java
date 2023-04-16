package com.bank.DTO;

import lombok.Data;

@Data
public class GetTransactionsRequest {
    private Long userId;
    private String accountType;
}
