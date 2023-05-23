package com.example.kitchenmanager.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RentMeetingRoomDTO {
    private String room;
    private String snack;
    private String ownerEmail;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
