package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_event_categories"))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_event_locations"))
    private Location location;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_event_prices"))
    private Price price;

}
