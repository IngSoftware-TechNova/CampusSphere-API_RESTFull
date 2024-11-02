package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class HomeEventController {

    private final EventService eventService;

    //Endpoint para obtener los 6 eventos mas recientes
    @GetMapping("/recent")
    public ResponseEntity<List<EventDetailsDTO>> getRecentEvents() {
        List<EventDetailsDTO> recentEvents = eventService.findTop8EventsByCreatedAt();
        return new ResponseEntity<>(recentEvents, HttpStatus.OK);
    }
}
