package com.technova.campussphereapi.api;
import com.technova.campussphereapi.service.PuntuacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.technova.campussphereapi.model.entity.Puntuacion;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/puntuacion")
public class PuntuacionController {

    private final PuntuacionService puntuacionService;

    @GetMapping
    public ResponseEntity<List<Puntuacion>> getAllPuntuacion(){
        List<Puntuacion> puntuacion = puntuacionService.getAll();
        return new ResponseEntity<>(puntuacion, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Puntuacion>> paginatePuntuacions(
            @PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<Puntuacion> puntuacion = puntuacionService.paginate(pageable);
        return new ResponseEntity<Page<Puntuacion>>(puntuacion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puntuacion> getPuntuacionById(@PathVariable("id") Integer id){
        Puntuacion puntuacion = puntuacionService.findById(id);
        return new ResponseEntity<Puntuacion>(puntuacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Puntuacion> createPuntuacion(@RequestBody Puntuacion puntuacion){
        Puntuacion newPuntuacion = puntuacionService.create(puntuacion);
        return new ResponseEntity<>(newPuntuacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Puntuacion> updatePuntuacion(@PathVariable("id") Integer id, @RequestBody Puntuacion puntuacion){
        Puntuacion updatePuntuacion = puntuacionService.update(id, puntuacion);
        return new ResponseEntity<>(updatePuntuacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntuacion(@PathVariable("id") Integer id){
        puntuacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
