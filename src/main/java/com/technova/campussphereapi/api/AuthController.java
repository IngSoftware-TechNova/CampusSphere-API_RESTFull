package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthController {

    private final EstudianteService estudianteService;

    @PostMapping("/register")
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante newEstudiante = estudianteService.create(estudiante);
        return new ResponseEntity<Estudiante>(newEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable("id") Integer id,
                                               @RequestBody Estudiante evento){
        Estudiante updateEvento = estudianteService.update(id, evento);
        return new ResponseEntity<Estudiante>(updateEvento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> deleteEstudiante(@PathVariable("id") Integer id){
        estudianteService.delete(id);
        return new ResponseEntity<Estudiante>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudiante(@PathVariable("id") Integer id){
        Estudiante estudiante = estudianteService.findById(id);
        return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
    }

}
