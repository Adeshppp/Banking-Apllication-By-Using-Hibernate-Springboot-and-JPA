package com.bank.service;

import com.bank.entity.User;

import java.util.List;

public interface UserService{
    User createUser(User user);
    void deleteUser(Long userId);
    User updateUser(Long userId, User newUser);
    User getUserById(Long id);
    List<User> getAllUsersAvailable();
    void deleteAllUsers();

}
