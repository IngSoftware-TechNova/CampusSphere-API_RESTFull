package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "programaciones_eventos")
public class ProgramacionEvento {
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}
