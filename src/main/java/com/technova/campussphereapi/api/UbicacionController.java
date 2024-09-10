package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Ubicacion;
import com.technova.campussphereapi.service.UbicacionService;
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
@RequestMapping("/ubicacion")
public class UbicacionController {
    private final UbicacionService ubicacionService;
    @GetMapping
    public ResponseEntity<List<Ubicacion>> listaUbicacion() {
        List<Ubicacion> ubicacion = ubicacionService.getAllUbicaciones();
        return new ResponseEntity<List<Ubicacion>>(ubicacion, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Ubicacion>> paginaUbicacion(
            @PageableDefault(size = 5, sort ="nombre") Pageable pageable) {
        Page<Ubicacion> ubicacion = ubicacionService.paginate(pageable);
        return new ResponseEntity<>(ubicacion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> buscarUbicacion(@PathVariable("id") Integer id) {
        Ubicacion ubicacion = ubicacionService.getUbicacionById(id);
        return new ResponseEntity<Ubicacion>(ubicacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ubicacion> crearUbicacion(@RequestBody Ubicacion ubicacion) {
        Ubicacion newUbicacion = ubicacionService.createUbicacion(ubicacion);
        return new ResponseEntity<Ubicacion>(newUbicacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable("id") Integer id, @RequestBody Ubicacion ubicacion) {
        Ubicacion updatedCategoria = ubicacionService.updateUbicacion(id, ubicacion);
        return new ResponseEntity<Ubicacion>( updatedCategoria,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ubicacion> deleteUbicacion(@PathVariable("id") Integer id) {
        ubicacionService.deleteUbicacion(id);
        return new ResponseEntity<Ubicacion>(HttpStatus.NO_CONTENT);
    }
}
