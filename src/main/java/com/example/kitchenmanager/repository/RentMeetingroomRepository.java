package com.example.kitchenmanager.repository;

import com.example.kitchenmanager.models.Employee;
import com.example.kitchenmanager.models.MeetingRoom;
import com.example.kitchenmanager.models.RentMeetingroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentMeetingroomRepository extends JpaRepository<RentMeetingroom, Long> {
    List<RentMeetingroom> findAllByOwner(Employee owner);
    List<RentMeetingroom> findAllByRoomAndStartTimeBefore(MeetingRoom room, LocalDateTime endTime);
    List<RentMeetingroom> findAllByRoomAndEndTimeAfter(MeetingRoom room, LocalDateTime startTime);
    List<RentMeetingroom> findAllByEndTimeAfter(LocalDateTime startTime);
    Optional<RentMeetingroom> findByRoomAndStartTimeEqualsAndEndTimeEquals(MeetingRoom room, LocalDateTime startTime, LocalDateTime endTime);

}
