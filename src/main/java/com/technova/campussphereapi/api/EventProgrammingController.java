package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.EventProgrammingDTO;
import com.technova.campussphereapi.dto.EventProgrammingCreateUpdateDTO;
import com.technova.campussphereapi.service.EventProgrammingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/event-programming")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')")
public class EventProgrammingController {

    private final EventProgrammingService eventProgrammingService;

    @PostMapping
    public ResponseEntity<EventProgrammingDTO> create(@Valid @RequestBody EventProgrammingCreateUpdateDTO dto) {
        EventProgrammingDTO createdEventProgramming = eventProgrammingService.create(dto);
        return ResponseEntity.ok(createdEventProgramming);
    }

    @PutMapping("/{eventsId}/{schedulesId}")
    public ResponseEntity<EventProgrammingDTO> update(@PathVariable int eventsId, @PathVariable int schedulesId, @RequestBody EventProgrammingCreateUpdateDTO dto) {
        EventProgrammingDTO updatedEventProgramming = eventProgrammingService.update(dto, eventsId, schedulesId);
        return ResponseEntity.ok(updatedEventProgramming);
    }

    @DeleteMapping("/{eventsId}/{schedulesId}")
    public ResponseEntity<Void> delete(@PathVariable int eventsId, @PathVariable int schedulesId) {
        eventProgrammingService.delete(eventsId, schedulesId);
        return ResponseEntity.noContent().build();  // Respuesta 204 No Content
    }

    @GetMapping
    public ResponseEntity<List<EventProgrammingDTO>> findAll() {
        List<EventProgrammingDTO> eventProgrammings = eventProgrammingService.findAll();
        return ResponseEntity.ok(eventProgrammings);
    }

    @GetMapping("/event/{eventsId}")
    public ResponseEntity<List<EventProgrammingDTO>> findByIdEvent(@PathVariable int eventsId) {
        List<EventProgrammingDTO> eventProgrammings = eventProgrammingService.findByIdEvent(eventsId);
        return ResponseEntity.ok(eventProgrammings);
    }

    @GetMapping("/schedule/{schedulesId}")
    public ResponseEntity<List<EventProgrammingDTO>> findByIdSchedule(@PathVariable int schedulesId) {
        List<EventProgrammingDTO> eventProgrammings = eventProgrammingService.findByIdSchedule(schedulesId);
        return ResponseEntity.ok(eventProgrammings);
    }
}