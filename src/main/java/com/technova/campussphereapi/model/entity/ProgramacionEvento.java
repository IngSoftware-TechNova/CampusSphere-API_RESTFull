package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "programaciones_eventos")
@IdClass(ProgramacionEventoPK.class)
public class ProgramacionEvento {

    @Id
    private Integer evento;

    @Id
    private Integer horario;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

}
