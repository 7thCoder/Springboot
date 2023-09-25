package com.example.Taco.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SortAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Taco.taco.Taco;
import com.example.Taco.taco.tacOrder;

@RestController
@RequestMapping(path = "api/tacos", produces = "applcation/json")
@CrossOrigin(origins = "http: //tacocloud:8080")
public class TacoController {
    private tacOrder tacorepo;

    public TacoController(TacoRepository tacoRepo){
        this.tacorepo = tacoRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());
        return tacorepo.findAll(page).getContent();
    }
}
