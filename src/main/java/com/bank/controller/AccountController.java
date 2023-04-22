package com.bank.controller;


import com.bank.DTO.*;
import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request) {
        //verify the details referring user
        Account createdAccount = accountService.createAccount(request.getUserId(), request.getAccountType());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Account> deleteAccount(@RequestBody DeleteAccountRequest request){
        accountService.deleteAccount(request.getUserId(), request.getAccountType());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/fundtransfer")
    public ResponseEntity<Account> transferFund(@RequestBody TransferFundRequest request){
        accountService.transferAmount(request.getFromAccountId(),request.getFromAccountType(),request.getToAccountId(), request.getToAccountType(),request.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/deposite")
    public ResponseEntity<Account> depositeMoney(@RequestBody DepositeMoneyRequest request){
        accountService.depositeAmount(request.getUserId(),request.getUserAccountType(),request.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Account> withdrawMoney(@RequestBody WithdrawMoneyRequest request){
        accountService.withdrawAmount(request.getUserId(),request.getUserAccountType(),request.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestBody GetTransactionsRequest request){
        List<Transaction> transactions = accountService.getTransactionByUserId(request.getUserId(), request.getAccountType());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Account>> getAllAccountsById(@PathVariable Long id){
        List<Account> allAccountsByID = accountService.getAllAccountsCreatedById(id);
        return ResponseEntity.ok(allAccountsByID);
    }


    //stack overflow error: calling tostring method infinitely
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> allAccounts = accountService.getAllAccountsCreated();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/balance")
    public ResponseEntity<Long> getAccountBalance(@RequestBody GetTransactionsRequest request){
        Long balance = accountService.getBalance(request.getUserId(), request.getAccountType());
        return ResponseEntity.ok(balance);
    }
}


