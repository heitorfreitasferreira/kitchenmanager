package com.example.kitchenmanager.auth;

import com.example.kitchenmanager.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register/employee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request, Role.COMMON));
    }
    @PostMapping("/register/cook")
    public ResponseEntity<AuthenticationResponse> registerCook(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request, Role.COOK));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
