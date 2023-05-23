package com.example.kitchenmanager.repository;

import com.example.kitchenmanager.models.Meals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealsRepository extends JpaRepository<Meals,Long> {

    Optional<Meals> findMealsByName(String name);
}
