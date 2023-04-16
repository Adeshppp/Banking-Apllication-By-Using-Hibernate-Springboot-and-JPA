
package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;
import com.bank.exceptions.NotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;


    public Account createAccount(User user, String accountType) {
        Account acc = getAccountByUser(user.getId(), accountType);
        if(acc == null){
            Account account = new Account();
            account.setUser(user);
            account.setAccountType(accountType);
            if(account.getBalance() == null) account.setBalance(0L);
            user.getAccounts().add(account);
            userRepository.save(user);
        }
//        throw new AccountExistException("account already exists for this user.");
        return acc;
    }



    public Account getAccountByUser(Long userId, String type){
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+userId));
        List<Account> accounts = user.getAccounts();
        for(Account account:accounts) if(Objects.equals(account.getAccountType(), type)) return account;
        return null;
    }


    public void deleteAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+userId));
        Account account = getAccountByUser(userId,accountType);
        user.getAccounts().remove(account);
        userRepository.save(user);
    }

    public void transferAmount(Long idFrom, String typeFrom, Long idTo, String typeTo, Long amount) {
        Account accountFrom = getAccountByUser(idFrom,typeFrom);
        Account accountTo = getAccountByUser(idTo,typeTo);
        accountFrom.setBalance(accountFrom.getBalance()-amount);
        accountTo.setBalance(accountTo.getBalance()+amount);
        accountFrom.getTransactions().add(addTransaction(accountTo,"Sent", amount));
        accountTo.getTransactions().add(addTransaction(accountFrom, "Received", amount));
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    public void depositeAmount(Long userId,String type,  Long amount) {
        Account account = getAccountByUser(userId, type);
        account.setBalance(account.getBalance()+amount);
        account.getTransactions().add(addTransaction(account,"Deposite",amount));
        accountRepository.save(account);
    }

    public void withdrawAmount(Long id,String type,  Long amount) {
        Account account = getAccountByUser(id, type);
        account.setBalance(account.getBalance()-amount);
        account.getTransactions().add(addTransaction(account,"Withdraw",amount));
        accountRepository.save(account);
    }

    public List<Transaction> getTransactionByUserId(Long userId, String accountType) {
        Account account= getAccountByUser(userId, accountType);
        return account.getTransactions();
    }


    public Transaction addTransaction(Account account, String transactionType, Long amount){
        Transaction transaction = new Transaction();
        if(Objects.equals(transactionType,"Sent"))transaction.setToAccountID(account);
        else if (Objects.equals(transactionType,"Received")) transaction.setFromAccountID(account);
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        return transaction;
    }




}



