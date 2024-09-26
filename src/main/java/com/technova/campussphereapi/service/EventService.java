package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.EventCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.dto.FilteredEventsDTO;
import com.technova.campussphereapi.model.entity.Event;

import java.math.BigDecimal;
import java.util.List;

public interface EventService {
    List<EventDetailsDTO> findAll();
    EventDetailsDTO findById(Integer id);
    List<FilteredEventsDTO> getEventsFiltered(BigDecimal precioMin, BigDecimal precioMax, String categoriaName, String ubicacion);

    EventDetailsDTO create(EventCreateUpdateDTO eventCreateDTO);
    EventDetailsDTO update(Integer id, EventCreateUpdateDTO eventUpdateDTO);
    void delete(Integer id);
}

