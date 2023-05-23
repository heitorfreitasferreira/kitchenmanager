package com.example.kitchenmanager.dtos;

import com.example.kitchenmanager.models.MeetingRoom;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MeetingRoomDTO {
    private String name;
    private Long id;
    public MeetingRoomDTO(MeetingRoom meetingRoom){
        name = meetingRoom.getName();
        id = meetingRoom.getId();
    }
}
