package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;
import com.technova.campussphereapi.service.InscriptionService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')") // Permitir solo a Student

public class InscriptionController {

    private final InscriptionService inscriptionService;

    @PostMapping
    public ResponseEntity<InscriptionDetailsDTO> create(@Valid @RequestBody InscriptionCreateUpdateDTO inscription) throws MessagingException {
        InscriptionDetailsDTO newInscription = inscriptionService.create(inscription);
        return new ResponseEntity<>(newInscription, HttpStatus.CREATED);
    }

    @GetMapping("/report")
    public ResponseEntity<List<InscriptionReportDTO>> getInscriptionPerEventReport(){
        List<InscriptionReportDTO> reports = inscriptionService.getInscriptionPerEventReport();
        return ResponseEntity.ok(reports);
    }

}
