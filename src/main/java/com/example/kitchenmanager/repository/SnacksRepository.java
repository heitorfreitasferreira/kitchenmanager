package com.example.kitchenmanager.repository;

import com.example.kitchenmanager.models.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnacksRepository extends JpaRepository<Snacks,Long> {

    Optional<Snacks> findByName(String name);
}
