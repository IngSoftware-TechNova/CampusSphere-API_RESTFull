package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);
    //Metodo para buscar un estudiante por gmail (Sera usado en la autenticacion)
    Optional<Student> findByEmail(String email);
    Optional<Student> findByNameAndEmail(String name, String email);
}
