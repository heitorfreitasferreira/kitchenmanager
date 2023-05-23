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
public class MeetingRoom {

    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
}
