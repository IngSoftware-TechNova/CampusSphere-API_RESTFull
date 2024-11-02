package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <Student, Integer> {
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    // Metodo para verificar si ya existe un estudiante con el mismo nombre y apellido,excepto el usuario actual
    boolean existsByFirstNameAndLastNameAndUserIdNot(String firstName, String lastName, Integer userId);
}
