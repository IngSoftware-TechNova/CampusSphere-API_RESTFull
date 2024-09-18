package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedules")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_hour", nullable = false)
    private LocalDateTime horaInicio;
    @Column(name = "end_hour", nullable = false)
    private LocalDateTime horaFin;
}
