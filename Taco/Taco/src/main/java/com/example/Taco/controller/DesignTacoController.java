package com.example.Taco.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.validation.Errors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import com.example.Taco.taco.Ingredients;
import com.example.Taco.taco.Ingredients.Type;
import com.example.Taco.taco.Taco;
import com.example.Taco.taco.tacOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacOrder")
public class DesignTacoController {
    
    @ModelAttribute
    public void addIngredientsToModel (Model model){
        List<Ingredients> ingredients = Arrays.asList(
            new Ingredients("FLTO","flour Tortilla",Type.WRAP),
            new Ingredients("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredients("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredients("CARN", "Carnitas", Type.PROTEIN),
            new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredients("LETC", "Lettuce", Type.VEGGIES),
            new Ingredients("CHED", "Cheddar", Type.CHEESE),
            new Ingredients("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredients("SLSA", "Salsa", Type.SAUCE),
            new Ingredients("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredients.Type.values();
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

    private Iterable<Ingredients> filterByType(List<Ingredients> ingredients, Type type){
        return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    }

        
}
