package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "puntuaciones")
public class Puntuacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_puntuacion_eventos"))
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_puntuacion-estudiantes"))
    private Estudiante estudiante;
}
