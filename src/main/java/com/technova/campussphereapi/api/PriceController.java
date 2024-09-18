package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.service.TarifarioService;
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
@RequestMapping("/admin/tarifario")
public class PriceController {
    private final TarifarioService tarifarioService;

    @GetMapping
    public ResponseEntity<List<Price>> getAllTarifas() {
        List<Price> tarifas = tarifarioService.findAll();
        return new ResponseEntity<List<Price>>(tarifas, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Price>> paginateTarifas(
            @PageableDefault(size = 5, sort = "precio")Pageable pageable) {
        Page<Price> tarifas = tarifarioService.paginate(pageable);
        return new ResponseEntity<Page<Price>>(tarifas, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Price> getTarifaById(@PathVariable("id") Integer id) {
        Price tarifa = tarifarioService.findById(id);
        return new ResponseEntity<Price>(tarifa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Price> createTarifa(@RequestBody Price tarifa) {
        Price newTarifa = tarifarioService.create(tarifa);
        return new ResponseEntity<Price>(newTarifa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updateTarifa(@PathVariable("id") Integer id,
                                              @RequestBody Price tarifa) {
        Price updateTarifa = tarifarioService.update(id,tarifa);
        return new ResponseEntity<Price>(updateTarifa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Price> deleteTarifa(@PathVariable("id") Integer id) {
        tarifarioService.delete(id);
        return new ResponseEntity<Price>(HttpStatus.NO_CONTENT);
    }
}
