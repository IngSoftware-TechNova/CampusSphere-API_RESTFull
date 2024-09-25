package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.service.InscriptionService;
import jakarta.validation.Valid;
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
    public ResponseEntity<InscriptionDetailsDTO> create(@Valid @RequestBody InscriptionCreateUpdateDTO inscription) {
        InscriptionDetailsDTO newInscription = inscriptionService.create(inscription);
        return new ResponseEntity<>(newInscription, HttpStatus.CREATED);
    }


}
