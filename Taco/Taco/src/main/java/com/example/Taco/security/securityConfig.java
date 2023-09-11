package com.example.Taco.security;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Taco.Repository.userRepository;
import com.example.Taco.User;

@Configuration
public class securityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder){
    //     List<UserDetails> userList = new ArrayList<>();
    //     userList.add(new User("Buzz", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

    //     return new InMemoryUserDetailsManager(userList);
    // }

    @Bean
    public UserDetailsService userDetailsService(userRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User ," + username + ", not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeRequests()
        .antMatchers("/design", "/orders")
        .access("hasRole('USER')")
        .antMatchers("/", "/**").access("permitAll()")
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/design")
        .and()
        .logout()
        .and()
        .build();
    
    } 

}
