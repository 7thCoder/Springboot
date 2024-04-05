package com.example.Taco.controller;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
// import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Taco.Repository.tacoRepository;
import com.example.Taco.taco.Ingredient;
import com.example.Taco.taco.Taco;



@RestController
@RequestMapping(path="/api/tacos", produces="application/json")
@CrossOrigin(origins="http://localhost:8080")  
public class TacoController{
    
    private tacoRepository tacoRepo;
    RestTemplate rest = new RestTemplate();

    public TacoController(tacoRepository tacoRepo){
        this.tacoRepo = tacoRepo;
    }

    // @GetMapping(params ="recent")
    // public Iterable<Taco> recentTacos() {
    //     PageRequest page = PageRequest.of(1, 12, Sort.by("createdAt").descending());
    //     return tacoRepo.findAll(page).getContent();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
    //     Optional<Taco> optTaco = tacoRepo.findById(id);

    //     if(optTaco.isPresent()){
    //         return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
    //     }

    //     return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    // }

    // @PostMapping(consumes = "application/json")
    // @ResponseStatus (HttpStatus.CREATED)
    // public Taco postTaco(@RequestBody Taco taco){
    //     return tacoRepo.save(taco);
    // }

    // @DeleteMapping("/{orderid}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // public void deleteOrder(@PathVariable("orderid") Long orderId){
    //     try{
    //         tacoRepo.deleteById(orderId);
    //     }
    //     catch(EmptyResultDataAccessException e){

    //     }
    // }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public Ingredient getIngredientById(String ingredientId){
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                                    Ingredient.class, ingredientId);
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}",
        ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
        ingredient, Ingredient.class);
    }


}
