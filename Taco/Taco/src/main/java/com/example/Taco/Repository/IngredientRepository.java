package com.example.Taco.Repository;

import com.example.Taco.taco.Ingredient;
import java.util.Optional;

public interface IngredientRepository {
    Optional <Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
