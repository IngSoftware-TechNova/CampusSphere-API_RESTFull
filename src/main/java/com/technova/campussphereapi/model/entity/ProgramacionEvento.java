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
    private Evento evento;

    @Id
    private Horario horario;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;


}
