package com.example.Taco.taco;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.example.Taco.taco.Ingredient;

import lombok.Data;

@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    
    @NotNull
    @Size(min=5, message="You must choose atleast one ingredient: ")
    private List <Ingredient> ingredients;
}