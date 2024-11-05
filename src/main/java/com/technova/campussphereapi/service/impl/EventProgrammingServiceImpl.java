package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventProgrammingCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventProgrammingDTO;
import com.technova.campussphereapi.dto.UserEventProgrammingDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventProgrammingMapper;
import com.technova.campussphereapi.model.entity.*;
import com.technova.campussphereapi.repository.*;
import com.technova.campussphereapi.service.EventProgrammingService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventProgrammingServiceImpl implements EventProgrammingService {

    private final EventProgrammingRepository eventProgrammingRepository;
    private final EventProgrammingMapper eventProgrammingMapper;
    private final EventRepository eventRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final InscriptionRepository inscriptionRepository;

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
    @Transactional(readOnly = true)
    public List<EventProgrammingDTO> findAll() {
        List<EventProgramming> eventProgrammings = eventProgrammingRepository.findAll();
        return eventProgrammings.stream()
                .map(eventProgrammingMapper::toDTO)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<UserEventProgrammingDTO> getUserEventProgramming() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }

        List<Object[]> results = eventProgrammingRepository.getUserEventProgramming(user.getStudent().getId());

        Map<String, UserEventProgrammingDTO> inscriptionsMap = new HashMap<>();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object[] result : results) {
            String inscriptionId = (String) result[0];
            inscriptionsMap.computeIfAbsent(inscriptionId, id -> {
                UserEventProgrammingDTO dto = new UserEventProgrammingDTO();
                dto.setInscriptionId(inscriptionId);
                dto.setInscriptionStatus((String) result[1]);
                dto.setStudentName((String) result[2]);
                dto.setTotal((String) result[3]);
                dto.setCreatedAt((String) result[4]);
                dto.setItems(new ArrayList<>());
                return dto;
            });

            UserEventProgrammingDTO.EventItem eventItem = new UserEventProgrammingDTO.EventItem();
            eventItem.setEventId((String) result[5]);
            eventItem.setEventName((String) result[6]);
            eventItem.setEventDescription((String) result[7]); // `event_description` está en el índice 7

            try {
                // Usa los índices correctos para `start_hour` y `end_hour`
                eventItem.setScheduleStart(LocalTime.parse((String) result[9]).format(timeFormatter)); // `start_hour` en índice 9
                eventItem.setScheduleEnd(LocalTime.parse((String) result[10]).format(timeFormatter));   // `end_hour` en índice 10
                eventItem.setEventStartDate(LocalDate.parse((String) result[11]).format(dateFormatter)); // `event_start_date` en índice 11
                eventItem.setEventEndDate(LocalDate.parse((String) result[12]).format(dateFormatter));   // `event_end_date` en índice 12
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date or time: " + e.getMessage() + ", value: " + result[8]);
                throw e;
            }

            inscriptionsMap.get(inscriptionId).getItems().add(eventItem);
        }


        return new ArrayList<>(inscriptionsMap.values());
    }
}