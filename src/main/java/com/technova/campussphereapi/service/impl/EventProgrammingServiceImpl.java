package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventProgrammingCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventProgrammingDTO;
import com.technova.campussphereapi.dto.UserEventProgrammingDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventProgrammingMapper;
import com.technova.campussphereapi.model.entity.*;
import com.technova.campussphereapi.repository.EventProgrammingRepository;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.ScheduleRepository;
import com.technova.campussphereapi.repository.UserRepository;
import com.technova.campussphereapi.service.EventProgrammingService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventProgrammingServiceImpl implements EventProgrammingService {

    private final EventProgrammingRepository eventProgrammingRepository;
    private final EventProgrammingMapper eventProgrammingMapper;
    private final EventRepository eventRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public EventProgrammingDTO create(EventProgrammingCreateUpdateDTO dto) {
        EventProgrammingPK id = new EventProgrammingPK();
        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Schedule schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        id.setEvent(event);
        id.setSchedule(schedule);
        boolean exists = eventProgrammingRepository.existsById(id);
        if (exists) {
            throw new RuntimeException("EventProgramming with this eventId and scheduleId already exists.");
        }
        EventProgramming eventProgramming = eventProgrammingMapper.toEntity(dto);
        eventProgramming.setEvent(event);
        eventProgramming.setSchedule(schedule);
        EventProgramming savedEventProgramming = eventProgrammingRepository.save(eventProgramming);
        return eventProgrammingMapper.toDTO(savedEventProgramming);
    }

    @Override
    @Transactional
    public EventProgrammingDTO update(EventProgrammingCreateUpdateDTO dto, int eventsId, int schedulesId) {
        EventProgrammingPK id = new EventProgrammingPK();
        Event event = eventRepository.findById(eventsId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Schedule schedule = scheduleRepository.findById(schedulesId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        id.setEvent(event);
        id.setSchedule(schedule);
        Optional<EventProgramming> existing = eventProgrammingRepository.findById(id);
        if (existing.isPresent()) {
            EventProgramming eventProgramming = existing.get();
            eventProgramming.setStartDate(dto.getStartDate());
            eventProgramming.setEndDate(dto.getEndDate());
            EventProgramming updated = eventProgrammingRepository.save(eventProgramming);
            return eventProgrammingMapper.toDTO(updated);
        } else {
            throw new RuntimeException("EventProgramming not found with the given eventId and scheduleId.");
}
    }

    @Override
    @Transactional
    public void delete(int eventsId, int schedulesId) {
        EventProgrammingPK id = new EventProgrammingPK();
        Event event = eventRepository.findById(eventsId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Schedule schedule = scheduleRepository.findById(schedulesId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        id.setEvent(event);
        id.setSchedule(schedule);
        boolean exists = eventProgrammingRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("EventProgramming not found with the given eventId and scheduleId.");
        }
        eventProgrammingRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventProgrammingDTO> findByIdEvent(int eventsId) {
        Event event = eventRepository.findById(eventsId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        List<EventProgramming> eventProgrammings = eventProgrammingRepository.findByEvent(event);
        return eventProgrammings.stream()
                .map(eventProgrammingMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventProgrammingDTO> findByIdSchedule(int schedulesId) {
        Schedule schedule = scheduleRepository.findById(schedulesId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        List<EventProgramming> eventProgrammings = eventProgrammingRepository.findBySchedule(schedule);
        return eventProgrammings.stream()
                .map(eventProgrammingMapper::toDTO)
                .toList();
    }

    @Override
    public List<EventProgrammingDTO> findAll() {
        List<EventProgramming> eventProgrammings = eventProgrammingRepository.findAll();
        return eventProgrammings.stream()
                .map(eventProgrammingMapper::toDTO)
                .toList();
    }

    @Override
    public List<UserEventProgrammingDTO> getUserEventProgramming() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }

        //Integer studentId = user.getId();

        List<Object[]> results = eventProgrammingRepository.getUserEventProgramming(user.getStudent().getId());

        // Mapeamos los resultados al DTO
        return results.stream().map(result -> {
            UserEventProgrammingDTO dto = new UserEventProgrammingDTO();
            dto.setEventName((String) result[0]);  // Columna 0: event_name
            dto.setEventDescription((String) result[1]);  // Columna 1: event_description

            // Convertimos java.sql.Time a java.time.LocalTime usando toLocalTime() y luego a String
            dto.setScheduleStart(((Time) result[2]).toLocalTime().toString());  // Columna 2: schedule_start
            dto.setScheduleEnd(((Time) result[3]).toLocalTime().toString());    // Columna 3: schedule_end

            // Convertimos java.sql.Date a String usando toString()
            dto.setEventStartDate(((Date) result[4]).toString());  // Columna 4: event_start_date
            dto.setEventEndDate(((Date) result[5]).toString());    // Columna 5: event_end_date
            return dto;
        }).toList();
    }
}
