package com.technova.campussphereapi.service;


import com.technova.campussphereapi.dto.EventProgrammingCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventProgrammingDTO;
import com.technova.campussphereapi.dto.UserEventProgrammingDTO;

import java.util.List;

public interface EventProgrammingService {

    EventProgrammingDTO create(EventProgrammingCreateUpdateDTO dto);
    EventProgrammingDTO update(EventProgrammingCreateUpdateDTO dto, int eventsId, int schedulesId);
    void delete(int eventsId, int schedulesId);
    List<EventProgrammingDTO> findByIdEvent(int eventsId);
    List<EventProgrammingDTO> findByIdSchedule(int schedulesId);
    List<EventProgrammingDTO> findAll();
    List<UserEventProgrammingDTO> getUserEventProgramming();
}
