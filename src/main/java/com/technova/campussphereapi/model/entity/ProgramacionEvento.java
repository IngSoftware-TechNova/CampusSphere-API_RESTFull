package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "programaciones_eventos")
public class ProgramacionEvento {
    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_programacion_evento_eventos"))
    private Evento evento;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @ManyToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_programacion_de_evento_horarios"))
    private Horario horario;
}
