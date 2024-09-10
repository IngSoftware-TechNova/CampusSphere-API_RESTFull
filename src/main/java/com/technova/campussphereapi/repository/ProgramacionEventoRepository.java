package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.ProgramacionEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ProgramacionEventoRepository extends JpaRepository<ProgramacionEvento, Long> {
}
