package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.model.entity.Tarifario;
import com.technova.campussphereapi.service.TarifarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/tarifario")
public class TarifarioController {
    private final TarifarioService tarifarioService;

    @GetMapping
    public ResponseEntity<List<Tarifario>> getAllTarifas() {
        List<Tarifario> tarifas = tarifarioService.getAll();
        return new ResponseEntity<List<Tarifario>>(tarifas, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Tarifario>> paginateTarifas(
            @PageableDefault(size = 5, sort = "precio")Pageable pageable) {
        Page<Tarifario> tarifas = tarifarioService.paginate(pageable);
        return new ResponseEntity<Page<Tarifario>>(tarifas, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tarifario> getTarifaById(@PathVariable("id") Integer id) {
        Tarifario tarifa = tarifarioService.findById(id);
        return new ResponseEntity<Tarifario>(tarifa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tarifario> createTarifa(@RequestBody Tarifario tarifa) {
        Tarifario newTarifa = tarifarioService.create(tarifa);
        return new ResponseEntity<Tarifario>(newTarifa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarifario> updateTarifa(@PathVariable("id") Integer id,
                                                  @RequestBody Tarifario tarifa) {
        Tarifario updateTarifa = tarifarioService.update(id,tarifa);
        return new ResponseEntity<Tarifario>(updateTarifa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarifario> deleteTarifa(@PathVariable("id") Integer id) {
        tarifarioService.delete(id);
        return new ResponseEntity<Tarifario>(HttpStatus.NO_CONTENT);
    }
}
