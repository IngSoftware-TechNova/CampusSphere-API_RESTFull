package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.service.StudentEventInterestService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-event-interest")
@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')") // Permitir solo a Student
public class StudentEventInterestController {
    private final StudentEventInterestService studentEventInterestService;

    public StudentEventInterestController(StudentEventInterestService studentEventInterestService) {
        this.studentEventInterestService = studentEventInterestService;
    }

    //Endopint para que el estudiante para marque interes en un evento

    @PostMapping
    public ResponseEntity<StudentEventInterestDTO> create(@RequestBody StudentEventInterestDTO studentEventInterestDTO) {
        StudentEventInterestDTO result = studentEventInterestService.create(studentEventInterestDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //Endpoint para obtener todos los interes de un studiante por su ID
    @GetMapping("/student/{id}")
    public ResponseEntity<List <StudentEventInterestDTO>>findByStudentId(@PathVariable Integer id) {
        List<StudentEventInterestDTO> result = studentEventInterestService.findByStudentId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    //Endpoint para obtener todos los intereses de un evento por us ID
    public ResponseEntity<List <StudentEventInterestDTO>>findByEventId(@PathVariable Integer id) {
     List<StudentEventInterestDTO> result = studentEventInterestService.findByEventId(id);
     return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
