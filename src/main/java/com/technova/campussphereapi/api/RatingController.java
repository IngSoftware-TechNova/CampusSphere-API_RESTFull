package com.technova.campussphereapi.api;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import com.technova.campussphereapi.dto.RatingCreateUpdateDTO;
import com.technova.campussphereapi.dto.RatingDetailsDTO;
import com.technova.campussphereapi.model.entity.Rating;
import com.technova.campussphereapi.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')") // Permitir solo a Student
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/report")
    public ResponseEntity<List<PuntuacionReportDTO>> getRatingReport(){
        List<PuntuacionReportDTO> reports = ratingService.getRateReportByDate();
        return ResponseEntity.ok(reports);
    }

    @GetMapping
    public ResponseEntity<List<RatingDetailsDTO>> list(){
        List<RatingDetailsDTO> ratings = ratingService.findAll();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDetailsDTO> search(@PathVariable("id") Integer id){
        RatingDetailsDTO rating = ratingService.findById(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RatingDetailsDTO> create(@Valid @RequestBody RatingCreateUpdateDTO rating){
        RatingDetailsDTO newRating = ratingService.create(rating);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDetailsDTO> update(@PathVariable("id") Integer id,
                                                   @Valid @RequestBody RatingCreateUpdateDTO rating){
        RatingDetailsDTO updateRating = ratingService.update(id, rating);
        return new ResponseEntity<>(updateRating, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rating> delete(@PathVariable("id") Integer id){
        ratingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}