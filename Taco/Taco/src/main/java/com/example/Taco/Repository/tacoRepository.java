package com.example.Taco.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Taco.taco.Taco;

public interface tacoRepository extends CrudRepository<Taco, Long> {
    
}
