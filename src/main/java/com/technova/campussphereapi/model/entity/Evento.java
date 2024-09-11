package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_evento_categorias"))
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_evento_ubicaciones"))
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "tarifario_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_evento_tarifarios"))
    private Tarifario tarifario;

}
