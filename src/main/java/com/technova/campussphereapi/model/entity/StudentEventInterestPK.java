package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
@Data
@Embeddable
@EqualsAndHashCode
public class StudentEventInterestPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_student_interest", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_interest_id"))
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_event_interest", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_event_Interest_id"))
    private Event event;
}
