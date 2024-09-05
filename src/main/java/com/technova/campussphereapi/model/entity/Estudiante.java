package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "email", nullable = false, length = 100, columnDefinition = "TEXT")
    private String email;

    @Column(name = "carrera", nullable = false, length = 100)
    private String carrera;

    @Column(name = "contraseña", nullable = false, length = 100)
    private String contraseña;
}
