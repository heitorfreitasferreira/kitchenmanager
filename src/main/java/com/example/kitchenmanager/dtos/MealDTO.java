package com.example.kitchenmanager.dtos;

import com.example.kitchenmanager.models.Meals;
import lombok.Getter;

@Getter

public class MealDTO {
    private String name;
    private Long id;
    public MealDTO(Meals meal){
        name = meal.getName();
        id = meal.getId();
    }
}
