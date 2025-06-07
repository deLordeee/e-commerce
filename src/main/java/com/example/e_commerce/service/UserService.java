package com.example.e_commerce.service;

import com.example.e_commerce.entity.User;
import com.example.e_commerce.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface UserService {
    User createUser(User user);
    void deleteUser(Long id);






    User updateUser(Long id, User updateUser);

    void assignRole(Long id, String role);

    List<User> getAllUsers();

    User getUserById(Long id);
}
