package com.example.Taco.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.Taco.User;

public interface userRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
