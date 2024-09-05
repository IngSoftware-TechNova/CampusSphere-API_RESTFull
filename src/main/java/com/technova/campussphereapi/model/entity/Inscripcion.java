package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiantes;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
