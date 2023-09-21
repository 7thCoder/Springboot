package com.example.Taco.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Taco.User;

import lombok.Data;

@Data
public class registrationForm {
    
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname, street, city, zip, phone);
    }
}
