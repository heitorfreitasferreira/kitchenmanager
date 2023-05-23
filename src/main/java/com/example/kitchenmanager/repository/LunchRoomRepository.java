package com.example.kitchenmanager.repository;

import com.example.kitchenmanager.models.LunchRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface LunchRoomRepository extends JpaRepository<LunchRoom, Long> {
    Optional<LunchRoom> findByLunchDay(LocalDate date);

}
