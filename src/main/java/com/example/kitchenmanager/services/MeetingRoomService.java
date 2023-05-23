package com.example.kitchenmanager.services;

import com.example.kitchenmanager.dtos.MeetingRoomDTO;
import com.example.kitchenmanager.dtos.RentMeetingRoomDTO;
import com.example.kitchenmanager.dtos.requests.CreateRentMeetingRoomRequest;
import com.example.kitchenmanager.models.MeetingRoom;
import com.example.kitchenmanager.models.RentMeetingroom;
import com.example.kitchenmanager.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingRoomService {

    @Autowired
    private MeetingRoomRepository roomRepository;
    @Autowired
    private RentMeetingroomRepository rentRepository;
    @Autowired
    private MealsRepository mealsRepository;
    @Autowired
    private SnacksRepository snacksRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void createRoom(String name){
        roomRepository.save(MeetingRoom.builder().name(name).build());
    }
    public List<MeetingRoomDTO> getAllRooms(){
        return roomRepository.findAll().stream().map(MeetingRoomDTO::new).toList();
    }
    public List<RentMeetingRoomDTO> getAllMeetings(){
        var rents = rentRepository.findAllByEndTimeAfter(LocalDateTime.now());
        var rentsDTO = new ArrayList<RentMeetingRoomDTO>();
        if(rents.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        for (RentMeetingroom rent: rents){
            rentsDTO.add(
                    RentMeetingRoomDTO.builder()
                            .room(rent.getRoom().getName())
                            .ownerEmail(rent.getOwner().getEmail())
                            .startTime(rent.getStartTime())
                            .endTime(rent.getEndTime())
                            .snack(rent.getSnack().getName())
                            .build()
            );
        }
        return rentsDTO;
    }
    public void rentRoom(CreateRentMeetingRoomRequest rentMeetingRoomRequest){
        var startTime = rentMeetingRoomRequest.getStartTime();
        var endTime = rentMeetingRoomRequest.getEndTime();
        if (endTime.isBefore(startTime))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        var roomId = rentMeetingRoomRequest.getRoomId();
        var roomOpt = roomRepository.findById(roomId);
        if(roomOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        var room = roomOpt.get();
        var roomInUseOpt = rentRepository
                .findByRoomAndStartTimeEqualsAndEndTimeEquals(room, startTime, endTime);
        if(roomInUseOpt.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        var roomsEndingAfterThisStarts = rentRepository
                .findAllByRoomAndEndTimeAfter(room,startTime);
        var roomsStartingBeforeThisEnds = rentRepository
                .findAllByRoomAndStartTimeBefore(room, endTime);

        for (RentMeetingroom roomAfterStart : roomsEndingAfterThisStarts){
            if(roomAfterStart.getStartTime().isAfter(endTime))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (RentMeetingroom roomBeforeEnd : roomsStartingBeforeThisEnds){
            if(roomBeforeEnd.getEndTime().isBefore(startTime))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        var snackId = rentMeetingRoomRequest.getSnackId();
        var snackOpt = snacksRepository.findById(snackId);
        if (snackOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        var snack = snackOpt.get();

        var employeeEmail = rentMeetingRoomRequest.getOwnerEmail();
        var employeeOpt = employeeRepository.findByEmail(employeeEmail);
        if (employeeOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        var owner = employeeOpt.get();
        var rentRoomToSave = RentMeetingroom.builder()
                .owner(owner)
                .startTime(startTime)
                .endTime(endTime)
                .snack(snack)
                .room(room)
                .build();
        rentRepository.save(rentRoomToSave);
    }
    public void removeRentRoom(Long id){
        rentRepository.deleteById(id);
    }

}
