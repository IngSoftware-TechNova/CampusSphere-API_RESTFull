package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @PostMapping
    public ResponseEntity<Inscription> create(@RequestBody Inscription inscription) {
        Inscription newInscription = inscriptionService.create(inscription);
        return new ResponseEntity<Inscription>(newInscription, HttpStatus.CREATED);
    }


}
