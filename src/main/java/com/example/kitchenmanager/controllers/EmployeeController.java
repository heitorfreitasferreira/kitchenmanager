package com.example.kitchenmanager.controllers;

import com.example.kitchenmanager.dtos.*;
import com.example.kitchenmanager.dtos.requests.CreateRentMeetingRoomRequest;
import com.example.kitchenmanager.dtos.requests.SignInToLunchRequest;
import com.example.kitchenmanager.repository.SnacksRepository;
import com.example.kitchenmanager.services.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @Autowired
    private MealsService mealsService;
    @Autowired
    private SnackService snackService;
    @Autowired
    private LunchRoomService lunchRoomService;
    @Autowired
    private MeetingRoomService meetingRoomService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> all(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/snacks")
    public ResponseEntity<List<SnackDTO>> getAllSnacks(){
        return new ResponseEntity<>(snackService.getMenu(),HttpStatus.OK);
    }
    @GetMapping("/meals")
    public ResponseEntity<List<MealDTO>> getAllMeals(){
        return new ResponseEntity<>(mealsService.getMenu(),HttpStatus.OK);
    }
    @PatchMapping("/sign-in-lunch")
    public ResponseEntity<HttpStatus> signInToLunch(@RequestBody SignInToLunchRequest req){
        lunchRoomService.addEmployee(req.getDate(), req.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("/sign-out-lunch")
    public ResponseEntity<HttpStatus> signOutToLunch(@RequestBody SignInToLunchRequest req){
        lunchRoomService.removeEmployee(req.getDate(),req.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/rent-room")
    public ResponseEntity<HttpStatus> rentMeetingRoom(@RequestBody CreateRentMeetingRoomRequest req){
        meetingRoomService.rentRoom(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/rent-room/{id}")
    public ResponseEntity<HttpStatus> dropRentMeetingRoom(@PathVariable Long id){
        meetingRoomService.removeRentRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rooms-rented")
    public ResponseEntity<List<RentMeetingRoomDTO>> getAllRents(){
        return new ResponseEntity<>(meetingRoomService.getAllMeetings(), HttpStatus.OK);
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<MeetingRoomDTO>> getAllRooms(){
        return new ResponseEntity<>(meetingRoomService.getAllRooms(), HttpStatus.OK);
    }

}
