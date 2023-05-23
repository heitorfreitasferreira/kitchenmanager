package com.example.kitchenmanager.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Meals {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;

}
