package com.example.kitchenmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Snacks {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true)
    private String name;
}
