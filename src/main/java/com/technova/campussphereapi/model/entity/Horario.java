package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;
    @Column(name = "hora_fin", nullable = false)
    private LocalDateTime horaFin;
}
