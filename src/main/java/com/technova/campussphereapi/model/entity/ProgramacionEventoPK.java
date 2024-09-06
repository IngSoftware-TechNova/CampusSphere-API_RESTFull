package com.technova.campussphereapi.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class ProgramacionEventoPK  implements Serializable {
    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_programacion_evento_eventos"))
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_programacion_de_evento_horarios"))
    private Horario horario;
}
