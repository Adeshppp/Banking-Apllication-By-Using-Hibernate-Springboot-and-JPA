package com.bank.service.Impl;

import com.bank.entity.Account;
import com.bank.entity.User;
import com.bank.exceptions.AccountExistException;
import com.bank.exceptions.NotFoundException;
import com.bank.repository.UserRepository;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    public User createUser(User user) {
        // Todo: perform validation checks before creating user
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+ userId));
        if(user.getAccounts().size()>0)  throw new AccountExistException("User have accounts.");
        List<Account> accounts = user.getAccounts();
        for(Account acc: accounts) accountService.deleteAccount(userId, acc.getAccountType());
        userRepository.delete(user);
    }

    public User updateUser(Long userId, User newUser) {
        User existingUser= userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with id "+userId));
        User Updateduser = User.builder()
                .id(existingUser.getId())
                .userName(newUser.getUserName()!=null?newUser.getUserName():existingUser.getUserName())
                .firstName(newUser.getFirstName()!=null?newUser.getFirstName():existingUser.getFirstName())
                .lastName(newUser.getLastName()!=null?newUser.getLastName():existingUser.getLastName())
                .email(newUser.getEmail()!=null?newUser.getEmail():existingUser.getEmail())
                .createdAt(existingUser.getCreatedAt())
                .build();

        return userRepository.save(Updateduser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found with id "+id));
    }

    public List<User> getAllUsersAvailable() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
