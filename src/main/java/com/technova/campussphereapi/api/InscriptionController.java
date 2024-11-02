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
    public ResponseEntity<InscriptionDetailsDTO> create(@RequestBody InscriptionCreateUpdateDTO inscription) throws MessagingException {

        InscriptionDetailsDTO newInscription = inscriptionService.create(inscription);
        return new ResponseEntity<>(newInscription, HttpStatus.CREATED);
    }

    /*
    @GetMapping("/report")
    public ResponseEntity<List<InscriptionReportDTO>> getInscriptionPerEventReport(){
        List<InscriptionReportDTO> reports = inscriptionService.getInscriptionPerEventReport();
        return ResponseEntity.ok(reports);
    }
    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<InscriptionDetailsDTO> delete(@PathVariable Integer eventId){
        inscriptionService.delete(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */

    @GetMapping
    public ResponseEntity<List<InscriptionDetailsDTO>> listAllInscriptions() {
        List<InscriptionDetailsDTO> inscriptions = inscriptionService.getAllInscription();
        return ResponseEntity.ok(inscriptions);
    }

    @GetMapping("/user")
    public ResponseEntity<List<InscriptionDetailsDTO>> getInscriptionHistory() {
        List<InscriptionDetailsDTO> inscriptionHistory = inscriptionService.getInscriptionHistoryByUserId();
        return ResponseEntity.ok(inscriptionHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscriptionDetailsDTO> getPurchaseById(@PathVariable Integer id) {
        InscriptionDetailsDTO inscription = inscriptionService.getInscriptionById(id);
        return ResponseEntity.ok(inscription);
    }

    // Endpoint para confirmar la compra (calcular total)
    @PutMapping("/confirm/{id}")
    public ResponseEntity<InscriptionDetailsDTO> confirmPurchase(@PathVariable Integer id) {
        InscriptionDetailsDTO confirmedInscription = inscriptionService.confirmInscription(id);
        return ResponseEntity.ok(confirmedInscription);
    }

}
