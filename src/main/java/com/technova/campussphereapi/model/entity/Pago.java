package com.technova.campussphereapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_pago_metodos_de_pago"))
    private MetodoDePago metodoDePago;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_pago_estudiantes"))
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_pago_eventos"))
    private Evento evento;
}
