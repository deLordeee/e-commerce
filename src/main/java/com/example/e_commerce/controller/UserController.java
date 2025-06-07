package com.example.e_commerce.controller;

import com.example.e_commerce.dto.RoleDTO;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println("Received user: " + user);
    return new ResponseEntity<>(userService.createUser(user) , HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteUser/{id}")
    public HttpStatus deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return  HttpStatus.NO_CONTENT;
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id , @RequestBody User updateUser){
        User user = userService.updateUser(id,updateUser);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
    @PutMapping("/assignRole/{id}")
    public HttpStatus assignUser(@PathVariable Long id , @RequestBody RoleDTO roleDTO){
        userService.assignRole(id , roleDTO.getRole());
        return HttpStatus.OK;
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUsers() , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getAUserById(@PathVariable Long id){

        return new ResponseEntity<>(userService.getUserById(id) , HttpStatus.OK);
    }

}
