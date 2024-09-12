package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.model.entity.Horario;
import com.technova.campussphereapi.model.entity.Tarifario;
import com.technova.campussphereapi.service.HorarioService;
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
@RequestMapping("/admin/horario")
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorarios() {
        List<Horario> horarios = horarioService.getAll();
        return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Horario>> paginateHorarios(
            @PageableDefault(size = 5, sort = "hora_inicio")Pageable pageable) {
        Page<Horario> horarios = horarioService.paginate(pageable);
        return new ResponseEntity<Page<Horario>>(horarios, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable("id") Integer id) {
        Horario horario = horarioService.findById(id);
        return new ResponseEntity<Horario>(horario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        Horario newHorario = horarioService.create(horario);
        return new ResponseEntity<Horario>(newHorario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable("id") Integer id,
                                                  @RequestBody Horario horario) {
        Horario updateHorario = horarioService.update(id,horario);
        return new ResponseEntity<Horario>(updateHorario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Horario> deleteHorario(@PathVariable("id") Integer id) {
        horarioService.delete(id);
        return new ResponseEntity<Horario>(HttpStatus.NO_CONTENT);
    }

}
