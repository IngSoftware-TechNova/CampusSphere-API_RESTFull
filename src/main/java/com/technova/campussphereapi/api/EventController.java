package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.EventCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDetailsDTO>> list(){
        List<EventDetailsDTO> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailsDTO> search(@PathVariable("id") Integer id){
        EventDetailsDTO event = eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventDetailsDTO> create(@Valid @RequestBody EventCreateUpdateDTO event){
        EventDetailsDTO newEvent = eventService.create(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDetailsDTO> update(@PathVariable("id") Integer id,
                                                @Valid @RequestBody EventCreateUpdateDTO event){
        EventDetailsDTO updateEvent = eventService.update(id, event);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> delete(@PathVariable("id") Integer id){
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

