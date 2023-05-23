package com.example.kitchenmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LunchRoom {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate lunchDay;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meals meal;

    @OneToMany
    private Set<Employee> clients;
}
