package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.EventDetailsDTO;

public interface EventService {
    EventDetailsDTO findById(Integer id);
}
