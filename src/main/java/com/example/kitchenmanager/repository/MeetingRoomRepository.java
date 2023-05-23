package com.example.kitchenmanager.repository;

import com.example.kitchenmanager.models.Employee;
import com.example.kitchenmanager.models.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom,Long> {


}
