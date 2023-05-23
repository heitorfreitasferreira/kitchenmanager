package com.example.kitchenmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class RentMeetingroom {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private MeetingRoom room;

    @ManyToOne
    private Employee owner;

    @ManyToOne
    private Snacks snack;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
