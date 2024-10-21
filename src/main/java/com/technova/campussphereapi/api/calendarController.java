package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.UserEventProgrammingDTO;
import com.technova.campussphereapi.service.EventProgrammingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class calendarController {
    private final EventProgrammingService eventProgrammingService;

    @GetMapping("/user")
    public ResponseEntity<List<UserEventProgrammingDTO>> getUserEventProgramming(@RequestParam Integer studentId) {
        List<UserEventProgrammingDTO> eventProgramming = eventProgrammingService.getUserEventProgramming(studentId);
        return ResponseEntity.ok(eventProgramming);
    }
}
