package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tarifarios")
public class Tarifario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "precio", nullable = false,columnDefinition = "DECIMAL")
    private BigDecimal precio;
    @Column(name = "descripcion",nullable = false,columnDefinition = "TEXT")
    private String descripcion;
}
