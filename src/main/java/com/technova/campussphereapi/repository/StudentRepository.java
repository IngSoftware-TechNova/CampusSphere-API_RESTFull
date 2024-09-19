package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
