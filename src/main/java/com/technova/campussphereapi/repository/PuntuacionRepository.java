package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Puntuacion;
import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.model.entity.Evento;
import com.technova.campussphereapi.model.entity.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Integer> {

    Optional<Puntuacion> findByPuntuacion(Integer puntuacion);
    Optional<Puntuacion> findByEventoAndEstudiante(Evento evento, Estudiante estudiante);

    @Query(value = "SELECT * FROM fn_list_puntuaciones_report() ", nativeQuery = true)
    List<Object[]> getPuntuacionReportByDate();
}