
package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;
import com.bank.exceptions.InsufficientFunds;
import com.bank.exceptions.NotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
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

    @Autowired
    TransactionRepository transactionRepository;

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

    public List<Account> getAllAccountsCreated(){
        return accountRepository.findAll();
    }

    public List<Account> getAllAccountsCreatedById(Long id) {
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
        int size=user.getAccounts().size();
        if(size==0) throw new NotFoundException("User does not have any account.");
        else if (size==1) {
            Account account = user.getAccounts().get(0);
            if (!Objects.equals(account.getAccountType(),accountType)) throw new NotFoundException("User does not have "+accountType+" account.");
            else {
                Account acc = getAccountByUser(userId,accountType);
                accountRepository.delete(acc);
//                user.getAccounts().remove(acc);
//                userRepository.save(user);
            }
        }
    }


    private boolean validateBalance(Long idFrom, String typeFrom, Long amount) {
        if(getBalance(idFrom, typeFrom)<amount) return false;
        return true;
    }

    public void transferAmount(Long idFrom, String typeFrom, Long idTo, String typeTo, Long amount) {
        if(validateBalance(idFrom,typeFrom, amount)) {
            Account accountFrom = getAccountByUser(idFrom, typeFrom);
            Account accountTo = getAccountByUser(idTo, typeTo);
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            accountFrom.getTransactions().add(addTransaction(accountFrom, accountTo, "Sent", amount));
            accountTo.getTransactions().add(addTransaction(accountFrom, accountTo, "Received", amount));
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
        }
        else throw new InsufficientFunds("Insufficient funds!");
    }

    public Transaction addTransaction(Account accountFrom, Account accountTo, String transactionType, Long amount){
        Transaction transaction = new Transaction();
        if(Objects.equals(transactionType,"Sent")) {
            transaction.setAccount(accountFrom);
            transaction.setToAccountID(accountTo.getId());
        }
        else if (Objects.equals(transactionType,"Received")) {
            transaction.setAccount(accountTo);
            transaction.setFromAccountID(accountFrom.getId());
        }
        else transaction.setAccount(accountFrom);
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        return transaction;
    }

    public void depositeAmount(Long userId,String type,  Long amount) {
        Account account = getAccountByUser(userId, type);
        account.setBalance(account.getBalance()+amount);
        account.getTransactions().add(addTransaction(account,null,"Deposite",amount));
        accountRepository.save(account);
    }

    public void withdrawAmount(Long id,String type,  Long amount) {
        if(validateBalance(id, type, amount)) {
            Account account = getAccountByUser(id, type);
            account.setBalance(account.getBalance() - amount);
            account.getTransactions().add(addTransaction(account, null, "Withdraw", amount));
            accountRepository.save(account);
        }
        else throw new InsufficientFunds("Insufficient funds!");
    }

    public List<Transaction> getTransactionByUserId(Long userId, String accountType) {
        return getAccountByUser(userId, accountType).getTransactions();
    }

    public Long getBalance(Long userId, String accountType) {
        return getAccountByUser(userId,accountType).getBalance();
    }
}



