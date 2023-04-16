package com.bank.DTO;

import lombok.Data;

@Data
public class DeleteAccountRequest {
    private Long userId;
    private String accountType;
}
