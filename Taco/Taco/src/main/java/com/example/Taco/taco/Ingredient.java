package com.example.Taco.taco;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private final String id;

    private final String name;
    
    private final Type type;

    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
