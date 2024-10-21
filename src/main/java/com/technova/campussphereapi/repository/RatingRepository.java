package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Rating;
import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Optional<Rating> findByRate(Integer rating);
        Optional<Rating> findByEventIdAndStudentId(Integer eventId, Integer studentId);

    @Query(value = "SELECT * FROM fn_list_puntuaciones_report() ", nativeQuery = true)
    List<Object[]> getPuntuacionReportByDate();
}