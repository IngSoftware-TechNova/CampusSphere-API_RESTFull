package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
