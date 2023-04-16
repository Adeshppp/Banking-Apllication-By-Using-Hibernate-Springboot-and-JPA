//package com.bank.service;
//
//import com.bank.entity.Account;
//import com.bank.entity.Transaction;
//import com.bank.repository.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//
//@Service
//public class TransactionService {
//    @Autowired
//    TransactionRepository transactionRepository;
//
//    @Autowired
//    AccountService accountService;
//
//    public List<Transaction> getTransactionByUserId(Long userId, String accountType) {
//        Account account= accountService.getAccountByUser(userId, accountType);
//        return account.getTransactions();
//    }
//
//
//    public Transaction addTransaction(Account accountId, String transactionType, Long amount){
//        Transaction transaction = new Transaction();
//        if(Objects.equals(transactionType,"Sent"))transaction.setToAccountID(accountId);
//        else if (Objects.equals(transactionType,"Received")) transaction.setFromAccountID(accountId);
//        transaction.setType(transactionType);
//        transaction.setAmount(amount);
//        return transaction;
////        account.getTransactions().add(transaction);
//
//    }
//}
//

//=====================================================================================================
