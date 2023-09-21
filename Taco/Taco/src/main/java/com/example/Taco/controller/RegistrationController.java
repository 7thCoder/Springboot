package com.example.Taco.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Taco.Repository.userRepository;
import com.example.Taco.security.registrationForm;
import com.example.Taco.security.userDetailsService;
import com.example.Taco.security.registrationForm;

@Controller
@RequestMapping("/register")
public class registrationController {
    
    private userRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public registrationController(
        userRepository userRepo, PasswordEncoder passwordEncoder
    ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration (registrationForm form){
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
