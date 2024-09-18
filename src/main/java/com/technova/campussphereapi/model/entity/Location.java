package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "city", columnDefinition = "Text")
    private String ciudad;

    @Column(name = "country", columnDefinition = "TEXT")
    private String pais;


}
