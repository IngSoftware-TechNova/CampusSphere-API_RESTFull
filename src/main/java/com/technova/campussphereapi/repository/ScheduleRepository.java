package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Horario, Integer> {

}
