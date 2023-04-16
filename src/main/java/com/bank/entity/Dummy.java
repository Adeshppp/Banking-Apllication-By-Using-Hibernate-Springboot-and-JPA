//import com.bank.entity.Account;
//import com.bank.exceptions.AccountExistException;
//import com.bank.repository.AccountRepository;
//import com.bank.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class AccountService {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Account createAccount(User user, String accountType) {
//        Account account = getAccountByUser(user.getId(), accountType);
//        if (account == null) {
//            account = new Account();
//            account.setUser(user);
//            account.setAccountType(accountType);
//            if (account.getBalance() == null) {
//                account.setBalance(0L);
//            }
//            user.addAccount(account);
//            userRepository.save(user);
//            return account;
//        } else {
//            throw new AccountExistException("account already exists for this user.");
//        }
//    }
//
//    public void deleteAccount(Long userId, String accountType) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));
//        Account account = getAccountByUser(userId, accountType);
//        user.removeAccount(account);
//        userRepository.save(user);
//    }
//
//    public void transferAmount(Long idFrom, String typeFrom, Long idTo, String typeTo, Long amount) {
//        Account accountFrom = getAccountByUser(idFrom, typeFrom);
//        Account accountTo = getAccountByUser(idTo, typeTo);
//        accountFrom.setBalance(accountFrom.getBalance() - amount);
//        accountTo.setBalance(accountTo.getBalance() + amount);
//        accountFrom.addTransaction(createTransaction(accountTo, "Sent", amount));
//        accountTo.addTransaction(createTransaction(accountFrom, "Received", amount));
//        accountRepository.save(accountFrom);
//        accountRepository.save(accountTo);
//    }
//
//    public void depositeAmount(Long userId, String accountType, Long amount) {
//        Account account = getAccountByUser(userId, accountType);
//        account.setBalance(account.getBalance() + amount);
//        account.addTransaction(createTransaction(account, "Deposit", amount));
//        accountRepository.save(account);
//    }
//
//    public void withdrawAmount(Long userId, String accountType, Long amount) {
//        Account account = getAccountByUser(userId, accountType);
//        account.setBalance(account.getBalance() - amount);
//        account.addTransaction(createTransaction(account, "Withdraw", amount));
//        accountRepository.save(account);
//    }
//
//    public List<Transaction> getTransactionsByUser(Long userId, String accountType) {
//        Account account = getAccountByUser(userId, accountType);
//        return account.getTransactions();
//    }
//
//    private Account getAccountByUser(Long userId, String accountType) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));
//        List<Account> accounts = user.getAccounts();
//        for (Account account : accounts) {
//            if (Objects.equals(account.getAccountType(), accountType)) {
//                return account;
//            }
//        }
//        return null;
//    }
//
//    private Transaction createTransaction(Account account, String transactionType, Long amount) {
//        Transaction transaction = new Transaction();
//        if (Objects.equals(transactionType, "Sent")) {
//            transaction.setToAccountID(account);
//        } else if (Objects.equals(transactionType, "Received")) {
//            transaction.setFromAccountID(account);
//        }
//        transaction.setType(transactionType);
//        transaction.setAmount(amount);
//        return transaction;
//    }
//}
////
