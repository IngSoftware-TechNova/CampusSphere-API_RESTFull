package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    Optional<Inscription> findByEventAndStudent(Event event, Student student);

}
