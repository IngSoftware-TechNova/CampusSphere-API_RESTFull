package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByNameAndDescription(String name, String description);
    List<Event> findTop8ByOrderByCreatedAtDesc();
}
