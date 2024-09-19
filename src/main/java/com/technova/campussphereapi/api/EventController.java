package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.service.EventService;
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
    public ResponseEntity<List<Event>> list(){
        List<Event> events = eventService.findAll();
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> search(@PathVariable("id") Integer id){
        Event event = eventService.findById(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event){
        Event newEvent = eventService.create(event);
        return new ResponseEntity<Event>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable("id") Integer id,
                                               @RequestBody Event event){
        Event updateEvent = eventService.update(id, event);
        return new ResponseEntity<Event>(updateEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> delete(@PathVariable("id") Integer id){
        eventService.delete(id);
        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
    }

}

