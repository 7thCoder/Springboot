package com.example.Taco.security;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class securityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public userDetailsService userDetailsService(PasswordEncoder encoder){
    //     List<userDetailsService> userList = new ArrayList<>();
    //     userList.add(new User("Buzz"))
    // }
}
