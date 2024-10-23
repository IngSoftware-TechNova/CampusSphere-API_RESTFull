package com.technova.campussphereapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inscription_items")
public class InscriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "FK_inscription_item_events"))
    private Event event;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inscription_id", referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "FK_inscription_item_inscription"))
    public Inscription inscription;
}
