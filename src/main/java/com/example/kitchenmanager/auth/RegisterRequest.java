package com.example.kitchenmanager.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
