package com.example.kitchenmanager.controllers;

import com.example.kitchenmanager.dtos.EmployeeDTO;
import com.example.kitchenmanager.dtos.LunchRoomDTO;
import com.example.kitchenmanager.dtos.SnackDTO;
import com.example.kitchenmanager.dtos.requests.CreateLunchRequest;
import com.example.kitchenmanager.dtos.MealDTO;
import com.example.kitchenmanager.dtos.requests.CreateMealRequest;
import com.example.kitchenmanager.dtos.requests.CreateSnackRequest;
import com.example.kitchenmanager.services.LunchRoomService;
import com.example.kitchenmanager.services.MealsService;
import com.example.kitchenmanager.services.MeetingRoomService;
import com.example.kitchenmanager.services.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cook")
public class CookController {
    @Autowired
    private LunchRoomService lunchRoomService;
    @Autowired
    private MealsService mealsService;
    @Autowired
    private SnackService snackService;
    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/lunch")
    public ResponseEntity<HttpStatus> createLunch(
            @RequestBody CreateLunchRequest request
    ){
        lunchRoomService.createLunch(request.getMeal(),request.getDate());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/meal")
    public ResponseEntity<HttpStatus> createMeal(@RequestBody CreateMealRequest meal){
        System.out.println("Request Body: "+ meal.toString());
//        if (mealsService.mealExists(meal)) {

            mealsService.addMeal(meal);
            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/meal/{id}")
    public ResponseEntity<HttpStatus> deleteMeal(@PathVariable Long id){
        try{
            mealsService.removeMeal(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/meals")
    public ResponseEntity<List<MealDTO>> getAllMeals(){
        return new ResponseEntity<>(mealsService.getMenu(),HttpStatus.OK);
    }
    @PostMapping("/snack")
    public ResponseEntity<HttpStatus> createSnack(@RequestBody CreateSnackRequest snack){
        snackService.add(snack);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/snack/{id}")
    public ResponseEntity<HttpStatus> deleteSnack(@PathVariable Long id){
        try{
            snackService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/snacks")
    public ResponseEntity<List<SnackDTO>> getAllSnacks(){
        return new ResponseEntity<>(snackService.getMenu(),HttpStatus.OK);
    }
    @PostMapping("/lunchroom")
    public ResponseEntity<HttpStatus> createLunchRoom(
            @RequestBody CreateLunchRequest lunchRequest
    ){
        lunchRoomService.createLunch(lunchRequest.getMeal(),lunchRequest.getDate());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/all-lunchrooms")
    public ResponseEntity<List<LunchRoomDTO>> getAllLunchRooms(){
        return new ResponseEntity<>(lunchRoomService.getAllLunchRooms(),HttpStatus.OK);
    }
    @GetMapping("/lunch-clients/{date}")
    public ResponseEntity<List<EmployeeDTO>> getClientsFromLunchRoomDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
        ){
        return new ResponseEntity<>(lunchRoomService.getClients(date),HttpStatus.OK);
    }
    @PostMapping("/meeting-room/{name}")
    public ResponseEntity<HttpStatus> createMeetingRoom(@PathVariable String name) {
        meetingRoomService.createRoom(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
