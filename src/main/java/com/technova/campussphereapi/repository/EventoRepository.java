package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
