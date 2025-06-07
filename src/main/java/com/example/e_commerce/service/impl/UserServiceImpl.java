package com.example.e_commerce.service.impl;

import com.example.e_commerce.entity.User;
import com.example.e_commerce.entity.UserRole;
import com.example.e_commerce.repository.UserRepository;
import com.example.e_commerce.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    @Override
    public void deleteUser(Long id){
    User user = userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User does not exist!"));
    userRepository.deleteById(id);

    }
    @Override
    public User updateUser(Long id, User updateUser){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist!"));

        if(updateUser.getFirstName() != null){
            user.setFirstName(updateUser.getFirstName());
        }
        if(updateUser.getLastName() != null){
            user.setLastName(updateUser.getLastName());
        }
        if(updateUser.getPhoneNumber() != null){
            user.setPhoneNumber(updateUser.getPhoneNumber());
        }
        if(updateUser.getLogin() != null){
            user.setLogin(updateUser.getLogin());
        }
        if(updateUser.getPassword() != null){
            user.setPassword(updateUser.getPassword());
        }
        return userRepository.save(user);
    }
    @Override
    public void assignRole(Long id, String role){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist!"));
        try {
            user.setRole(UserRole.valueOf(role.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
        userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }
    @Override
    public User getUserById(Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User does not exist!"));
        return user;
    }
}
