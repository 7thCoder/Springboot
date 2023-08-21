package com.example.Taco.taco;

import java.util.List;

import lombok.Data;

@Data
public class Taco {
 private String name;
 
 private List<Ingredients> ingredients;
}