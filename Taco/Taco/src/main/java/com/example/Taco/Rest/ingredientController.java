package com.example.Taco.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Taco.Repository.IngredientRepository;
import com.example.Taco.taco.Ingredient;

@RestController
@RequestMapping(path ="/api/ingredients", produces = "applicaton/json")
@CrossOrigin(origins="http://localhost:8080")
public class ingredientController {
    
    private IngredientRepository repo;

    @Autowired
    public ingredientController(IngredientRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable ("id") String ingredientId){
        repo.deleteById(ingredientId);
    }

    
}
