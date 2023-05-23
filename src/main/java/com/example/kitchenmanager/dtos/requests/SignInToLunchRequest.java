package com.example.kitchenmanager.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data

public class SignInToLunchRequest {
    private LocalDate date;
    private String email;
}
