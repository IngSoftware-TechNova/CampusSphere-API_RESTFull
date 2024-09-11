package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Inscripcion;
import com.technova.campussphereapi.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<Inscripcion> createInscripcion(@RequestBody Inscripcion inscripcion) {
        Inscripcion newInscripcion = inscripcionService.create(inscripcion);
        return new ResponseEntity<Inscripcion>(newInscripcion, HttpStatus.CREATED);
    }


}
