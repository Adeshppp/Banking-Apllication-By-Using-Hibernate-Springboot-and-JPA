
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

    UserService userService;

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found with id "+id));
    }

    public Account createAccount(Long userId, String accountType) {
        Account acc = getAccountByUser(userId, accountType);
        if(acc == null){
            Account account = new Account();
            account.setUser(getUserById(userId));
            account.setAccountType(accountType);
            if(account.getBalance() == null) account.setBalance(0L);
            accountRepository.save(account);
            return account;

//            accountRepository.save(new Account(userService.getUserById(userId),accountType,0L));
    }
//        throw new AccountExistException("account already exists for this user.");
        return acc;
    }

    public List<Account> getAllAccountsCreated(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found with id "+id));
//        return user.getAccounts();
        return accountRepository.findByUserId(id);
    }



    public Account getAccountByUser(Long userId, String type){
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+userId));
        List<Account> accounts = user.getAccounts();
        for(Account account:accounts) if(Objects.equals(account.getAccountType(), type)) return account;
        return null;
    }


    public void deleteAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+userId));
        if(user.getAccounts().size()==0) throw new NotFoundException("User does not have any account.");
        else if (user.getAccounts().size()==1) {
            Account account = (Account) user.getAccounts();
            if (account.getAccountType()!=accountType) throw new NotFoundException("User does not have "+accountType+" account.");
        }
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



