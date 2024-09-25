package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByNameAndEmail(String name, String email);

}
