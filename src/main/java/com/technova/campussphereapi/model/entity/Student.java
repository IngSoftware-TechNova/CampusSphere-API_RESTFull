package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 100, columnDefinition = "TEXT")
    private String email;

    @Column(name = "career", nullable = false, length = 100)
    private String career;

    @Column(name = "password", nullable = false, length = 100)
    private String password;
}
