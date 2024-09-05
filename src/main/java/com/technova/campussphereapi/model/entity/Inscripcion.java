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
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_inscripcion_estudiantes"))
    private Estudiante estudiantes;

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_inscripcion_eventos"))
    private Evento evento;

}
