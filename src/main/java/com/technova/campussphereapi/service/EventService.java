package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.*;
import com.technova.campussphereapi.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface EventService {
    List<EventDetailsDTO> findAll();
    Page<EventDetailsDTO> paginate(Pageable pageable);
    EventDetailsDTO findById(Integer id);
    List<FilteredEventsDTO> getEventsFiltered(Float precioMin, Float precioMax, String categoriaName, String ubicacion);

    EventDetailsDTO create(EventCreateUpdateDTO eventCreateDTO);
    EventDetailsDTO update(Integer id, EventCreateUpdateDTO eventUpdateDTO);
    void delete(Integer id);

    List<EventDetailsDTO> findTop8EventsByCreatedAt();
    List<AdminEventSalesReportDTO> getAdminEventSalesReport();
}

