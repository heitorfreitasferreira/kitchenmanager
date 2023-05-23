package com.example.kitchenmanager.dtos.requests;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateLunchRequest {
    private LocalDate date;
    private Long meal;
}
