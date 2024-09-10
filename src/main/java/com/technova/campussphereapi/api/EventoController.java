package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Evento;
import com.technova.campussphereapi.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos(){
        List<Evento> eventos = eventoService.getAll();
        return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable("id") Integer id){
        Evento evento = eventoService.findById(id);
        return new ResponseEntity<Evento>(evento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento){
        Evento newEvento = eventoService.create(evento);
        return new ResponseEntity<Evento>(newEvento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable("id") Integer id,
                                               @RequestBody Evento evento){
        Evento updateEvento = eventoService.update(id, evento);
        return new ResponseEntity<Evento>(updateEvento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> deleteEvento(@PathVariable("id") Integer id){
        eventoService.delete(id);
        return new ResponseEntity<Evento>(HttpStatus.NO_CONTENT);
    }

}

