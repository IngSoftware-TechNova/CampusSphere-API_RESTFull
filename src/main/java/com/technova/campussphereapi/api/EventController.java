package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.EventCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')") // Aplicar la restriccion a nivel de clase
//@PreAuthorize("hasAnyRole('ADMIN','WORKER')") // Permitir a admin y worker si es que hubiera uno

public class EventController {

    private final EventService eventService;

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping
    public ResponseEntity<List<EventDetailsDTO>> list(){
        List<EventDetailsDTO> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<Page<EventDetailsDTO>> paginate(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<EventDetailsDTO> events = eventService.paginate(pageable);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<EventDetailsDTO> search(@PathVariable("id") Integer id){
        EventDetailsDTO event = eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventDetailsDTO> create(@Valid @RequestBody EventCreateUpdateDTO event){
        EventDetailsDTO newEvent = eventService.create(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EventDetailsDTO> update(@PathVariable("id") Integer id,
                                                @Valid @RequestBody EventCreateUpdateDTO event){
        EventDetailsDTO updateEvent = eventService.update(id, event);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> delete(@PathVariable("id") Integer id){
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

