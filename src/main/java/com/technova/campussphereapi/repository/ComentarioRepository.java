package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Comentario;
import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Optional<Comentario> findByComentario(String comentario);
    Optional<Comentario> findByEventoAndEstudiante(Evento evento, Estudiante estudiante);

    @Query(value = "SELECT * FROM fn_list_comentarios_report() ", nativeQuery = true)
    List<Object[]> getComentarioReportByDate();
}