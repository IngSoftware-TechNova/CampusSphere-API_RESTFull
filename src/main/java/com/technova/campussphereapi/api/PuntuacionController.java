package com.technova.campussphereapi.api;
import com.technova.campussphereapi.dto.PuntuacionDTO;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import com.technova.campussphereapi.service.PuntuacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/puntuacion")
public class PuntuacionController {

    private final PuntuacionService PuntuacionService;

    @GetMapping
    public ResponseEntity<List<PuntuacionDTO>> getAllPuntuacion(){
        List<PuntuacionDTO> puntuaciones = PuntuacionService.getAll();
        return new ResponseEntity<>(puntuaciones, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PuntuacionDTO>> paginatePuntuaciones(
            @PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<PuntuacionDTO> puntuacion = PuntuacionService.paginate(pageable);
        return new ResponseEntity<>(puntuacion, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<List<PuntuacionReportDTO>> getPuntuacionReport(){
        List<PuntuacionReportDTO> reports = PuntuacionService.getPuntuacionReportByDate();
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PuntuacionDTO> getById(@PathVariable Integer id){
        PuntuacionDTO puntuacion = PuntuacionService.findById(id);
        return new ResponseEntity<>(puntuacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PuntuacionDTO> create(@Valid @RequestBody PuntuacionDTO PuntuacionDTO){
        PuntuacionDTO createdPuntuacion = PuntuacionService.create(PuntuacionDTO);
        return new ResponseEntity<>(createdPuntuacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntuacionDTO> update(@PathVariable Integer id,@Valid @RequestBody PuntuacionDTO PuntuacionDTO){
        PuntuacionDTO updatePuntuacion = PuntuacionService.update(id, PuntuacionDTO);
        return new ResponseEntity<>(updatePuntuacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntuacion(@PathVariable Integer id){
        PuntuacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}