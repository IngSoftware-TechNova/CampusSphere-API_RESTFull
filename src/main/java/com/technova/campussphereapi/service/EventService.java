package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> findAll();
    Event findById(Integer id);
    Event create(Event event);
    Event update(Integer id, Event updateEvent);
    void delete(Integer id);
}

