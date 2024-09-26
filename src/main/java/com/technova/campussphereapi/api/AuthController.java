package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.StudentProfileDTO;
import com.technova.campussphereapi.dto.StudentRegistrationDTO;
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthController {
    private final StudentService estudianteService;

    @PostMapping("/register")
    public ResponseEntity<StudentRegistrationDTO> create(@Valid @RequestBody StudentRegistrationDTO studentRegistrationDTO) {
        StudentRegistrationDTO createStudent = estudianteService.create(studentRegistrationDTO);
        return new ResponseEntity<StudentRegistrationDTO>(createStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentRegistrationDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody StudentRegistrationDTO studentRegistrationDTO){
        StudentRegistrationDTO updateStudent = estudianteService.update(id, studentRegistrationDTO);
        return new ResponseEntity<StudentRegistrationDTO>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Integer id){
        estudianteService.delete(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> findById(@PathVariable("id") Integer id){
        StudentProfileDTO student = estudianteService.findById(id);
        return new ResponseEntity<StudentProfileDTO>(student, HttpStatus.OK);
    }

}
