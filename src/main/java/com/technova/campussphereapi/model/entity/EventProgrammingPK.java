package com.technova.campussphereapi.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class EventProgrammingPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_event_programming", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_event_programming_id"))
    private Event event;

    @ManyToOne
    @JoinColumn(name = "id_schedules_programmings", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_schedules_id"))
    private Schedule schedule;
}
