package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.PriceDTO;
import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.service.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/price")
@RestController
@PreAuthorize("hasRole('ADMIN')") // Aplicar la restriccion a nivel de clase

public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<List<PriceDTO>> list() {
        List<PriceDTO> prices = priceService.findAll();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PriceDTO>> paginate(@PageableDefault(size = 5, sort = "precio")
                                                       Pageable pageable) {
        Page<PriceDTO> price = priceService.paginate(pageable);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PriceDTO> search(@PathVariable("id") Integer id) {
        PriceDTO price = priceService.findById(id);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PriceDTO> create(@Valid @RequestBody PriceDTO priceDTO) {
        PriceDTO newPrice = priceService.create(priceDTO);
        return new ResponseEntity<>(newPrice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody PriceDTO priceDTO) {
        PriceDTO updatedPrice = priceService.update(id,priceDTO);
        return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Price> delete(@PathVariable("id") Integer id) {
        priceService.delete(id);
        return new ResponseEntity<Price>(HttpStatus.NO_CONTENT);
    }
}
