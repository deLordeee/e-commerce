package com.example.e_commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainWebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
