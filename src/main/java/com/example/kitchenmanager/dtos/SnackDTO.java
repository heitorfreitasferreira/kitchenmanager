package com.example.kitchenmanager.dtos;

import com.example.kitchenmanager.models.Snacks;
import lombok.Getter;

@Getter
public class SnackDTO {
    private String name;
    private Long id;
    public SnackDTO(Snacks snack){
        name = snack.getName();
        id = snack.getId();
    }
}
