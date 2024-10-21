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
    private String location;

    @Column(name = "city", columnDefinition = "Text")
    private String city;

    @Column(name = "country", columnDefinition = "TEXT")
    private String country;
}