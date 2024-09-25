package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    Optional<Inscription> findByEventAndStudent(Event event, Student student);

    @Query(value = "SELECT * FROM fn_list_inscriptions_per_event_report()", nativeQuery = true)
    List<Object[]> getInscriptionPerEventReport();

}
