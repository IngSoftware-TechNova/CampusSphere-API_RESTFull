package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_hour", nullable = false)
    private LocalTime startHour;
    @Column(name = "end_hour", nullable = false)
    private LocalTime endHour;
    @Column(name = "description", nullable = false)
    private String description;
}
