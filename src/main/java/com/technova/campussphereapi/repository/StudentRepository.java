package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <Student, Integer> {

    boolean existsByEmail(String email);
    //Metodo para buscar un estudiante por gmail (Sera usado en la autenticacion)
    Optional<Student> findByEmail(String email);
}
