package com.example.kitchenmanager.dtos;

import com.example.kitchenmanager.models.Employee;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmployeeDTO {
    private String email;
    private String fullName;
    private String role;
    public EmployeeDTO(Employee employee){
        email=employee.getEmail();
        fullName=employee.getFirstname()+ " " +employee.getLastname();
        role=employee.getRole().toString();
    }
}
