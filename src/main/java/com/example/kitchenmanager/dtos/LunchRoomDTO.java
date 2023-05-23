package com.example.kitchenmanager.dtos;

import com.example.kitchenmanager.models.LunchRoom;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LunchRoomDTO {
    private Long id;
    private LocalDate lunchDay;
    private String meal;

    public LunchRoomDTO(LunchRoom lunchRoom){
        id = lunchRoom.getId();
        lunchDay = lunchRoom.getLunchDay();
        meal = lunchRoom.getMeal().getName();
    }
}
