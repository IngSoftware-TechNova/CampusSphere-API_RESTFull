package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // Método para buscar si hay una descripcion repetida
    Optional<Schedule> findByDescription(String description);

    // Método para buscar si hay un horario solapado
    Optional<Schedule> findByStartHourAndEndHour(LocalTime startHour, LocalTime endHour);

}
