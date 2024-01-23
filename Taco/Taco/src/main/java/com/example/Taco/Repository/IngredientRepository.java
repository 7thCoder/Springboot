package com.example.Taco.Repository;

import com.example.Taco.taco.Ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    
    // Iterable<Ingredient> findAll();

    // Optional <Ingredient> findById(String id);

    // Ingredient save(Ingredient ingredient);
}
