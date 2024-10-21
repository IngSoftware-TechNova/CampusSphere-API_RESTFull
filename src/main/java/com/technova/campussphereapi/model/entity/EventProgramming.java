package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "events_programming")
@IdClass(EventProgrammingPK.class)
public class EventProgramming {
    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Schedule schedule;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

}
