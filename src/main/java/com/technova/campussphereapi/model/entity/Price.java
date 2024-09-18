package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price", nullable = false,columnDefinition = "DECIMAL")
    private BigDecimal precio;
    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String descripcion;
}
