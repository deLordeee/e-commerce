package com.example.e_commerce.controller;

import com.example.e_commerce.entity.User;
import com.example.e_commerce.entity.UserRole;
import com.example.e_commerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/users")
public class UserWebController {

    private final UserService userService;

    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRole.values());
        return "users/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/web/users";
    }

    @PostMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/web/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/web/users";
    }

    @GetMapping("/assign-role/{id}")
    public String showAssignRoleForm(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        model.addAttribute("roles", UserRole.values());
        return "users/assign-role";
    }

    @PostMapping("/assign-role/{id}")
    public String assignRole(@PathVariable Long id, @RequestParam String role) {
        userService.assignRole(id, role);
        return "redirect:/web/users";
    }
}