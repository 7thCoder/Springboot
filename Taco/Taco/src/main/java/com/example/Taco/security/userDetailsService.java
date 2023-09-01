package com.example.Taco.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface userDetailsService {
    UserDetails loadUserByName (String username) throws UsernameNotFoundException;
}
