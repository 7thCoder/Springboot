package com.example.Taco.controller;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Taco.Repository.tacoRepository;
import com.example.Taco.taco.Taco;



@RestController
@RequestMapping(path = "api/tacos", produces = "applcation/json")
@CrossOrigin(origins = "http: //tacocloud:8080")
public class TacoController {
    private tacoRepository tacoRepo;

    public TacoController(tacoRepository tacoRepo){
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pag = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(pag).getContent();
    }

}
