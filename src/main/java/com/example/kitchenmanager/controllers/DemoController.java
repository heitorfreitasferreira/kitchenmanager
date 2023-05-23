package com.example.kitchenmanager.controllers;

import com.example.kitchenmanager.models.Employee;
import com.example.kitchenmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    @Autowired
    EmployeeRepository repository;
    @GetMapping("/helloworld")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }
    @GetMapping("/getUser")
    public ResponseEntity<String>getUser(){
        Optional<Employee> heitor = repository.findByEmail("heitor@mail.com");
        if (heitor.isPresent()){
            return new ResponseEntity<>(heitor.get().toString(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
