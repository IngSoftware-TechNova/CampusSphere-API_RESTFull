package com.technova.campussphereapi.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "Updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_rating_events"))
    private Event event;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_rating_students"))
    private Student student;
}
