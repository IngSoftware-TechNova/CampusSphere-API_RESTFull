package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comentarios")
public class Comentario { //estos son los atributo de la clase comentario que tiene|
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "texto",nullable = false,columnDefinition = "TEXT")
    private String texto;

    @Column(name = "fecha_comentar")
    private LocalDateTime fechaComentar;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_comentario_eventos"))
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_comentario-estudiantes"))
    private Estudiante estudiante;

}
