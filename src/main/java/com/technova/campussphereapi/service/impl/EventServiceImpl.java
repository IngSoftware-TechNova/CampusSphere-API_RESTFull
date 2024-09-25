package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventMapper;
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
    private final EventMapper eventMapper;

    @Transactional(readOnly = true)
    @Override
    public EventDetailsDTO findById(Integer id) {

        Event event = eventRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con id: " + id));

        return eventMapper.toDetailsDTO(event);
    }
}
