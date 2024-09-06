package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.ProgramacionEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ProgramacionEventoRepository extends JpaRepository<ProgramacionEvento, Long> {

    @Modifying
    @Query(value = "INSERT INTO programaciones_eventos (evento_id, horario_id, fecha_inicio, fecha_fin) " +
            "VALUES (:eventoId, :horarioId, :fechaInicio, :fechaFin)", nativeQuery = true)
    void insertProgramacionEvento(@Param("eventoId") Long eventoId,
                                  @Param("horarioId") Long horarioId,
                                  @Param("fechaInicio") LocalDateTime fechaInicio,
                                  @Param("fechaFin") LocalDateTime fechaFin);

    @Modifying
    @Query(name= "update eventos e set enabled = false where event_id = :event_id", nativeQuery = true)
    void deactivateUsersNotLoggedInSince(@Param("event_id") int event_id);
}
