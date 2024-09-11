package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository <Estudiante, Integer> {
    boolean existsEstudianteByEmail(String email);

}
