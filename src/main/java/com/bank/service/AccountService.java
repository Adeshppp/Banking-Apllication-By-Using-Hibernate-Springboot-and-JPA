package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;

import java.util.List;

public interface AccountService {
    User getUserById(Long id);
    Account createAccount(Long userId, String accountType);
    List<Account> getAllAccountsCreated();
    List<Account> getAllAccountsCreatedById(Long id);
    Account getAccountByUser(Long userId, String type);
    void deleteAccount(Long userId, String accountType);
    void transferAmount(Long idFrom, String typeFrom, Long idTo, String typeTo, Long amount);
    Transaction addTransaction(Account accountFrom, Account accountTo, String transactionType, Long amount);
    void depositeAmount(Long userId,String type,  Long amount);
    void withdrawAmount(Long id,String type,  Long amount);
    List<Transaction> getTransactionByUserId(Long userId, String accountType);
    Long getBalance(Long userId, String accountType);


}
