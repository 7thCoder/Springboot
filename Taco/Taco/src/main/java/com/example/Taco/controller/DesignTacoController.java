package com.example.Taco.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// import javax.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import com.example.Taco.taco.Ingredient;
import com.example.Taco.taco.Ingredient.Type;

import com.example.Taco.taco.Taco;
import com.example.Taco.taco.tacOrder;
import com.example.Taco.Repository.*;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    
    public DesignTacoController(@Autowired IngredientRepository ingredientRepo)
    {
        this.ingredientRepo = ingredientRepo;
    }

    // @ModelAttribute
    // public void addIngredientsToModel (Model model){
    //     Iterable <Ingredient> ingredients = ingredientRepo.findAll();
        
    //     Type[] types = Ingredient.Type.values();
    //      for (Type type: types){
    //          model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type)); 
    //      }
    // }

    @ModelAttribute
    public void addIngredientsToModel (Model model){
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO","flour Tortilla",Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type)); 
        }
    }

        
    @ModelAttribute(name = "tacOrder")
    public tacOrder order(){
        return new tacOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showdesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco,Errors errors,  @ModelAttribute tacOrder tacOrder){
        if(errors.hasErrors()){
            return "design";
        }
        tacOrder.addTaco(taco);
        log.info("Processing taco: {}",taco);

        return "redirect:/orders/current";
       
    }


    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
    List<Ingredient> ingredientList = StreamSupport.stream(ingredients.spliterator(), false)
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
    return ingredientList;
}
    // private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
    //     return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    // }

        
}
