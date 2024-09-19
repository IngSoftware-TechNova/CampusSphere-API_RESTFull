package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    @Override
    public Event findById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento not found"));
    }
}
