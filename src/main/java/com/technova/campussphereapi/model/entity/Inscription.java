package com.technova.campussphereapi.model.entity;

import com.technova.campussphereapi.model.enums.InscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Inscriptions")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float total;

    @Column(name = "inscription_date")
    private LocalDateTime inscriptionDate;

    @Enumerated(EnumType.STRING)
    private InscriptionStatus inscriptionStatus;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_inscription_events"))
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_inscription_user"))
    private User user;

    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL)
    @Column(name = "events_id")
    private List<InscriptionItem> items;

}
