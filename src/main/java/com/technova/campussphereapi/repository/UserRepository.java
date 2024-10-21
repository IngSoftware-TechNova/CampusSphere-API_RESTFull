package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    //Metodo para buscar un estudiante por gmail (Sera usado en la autenticacion)
    Optional<User> findByEmail(String email);
}
