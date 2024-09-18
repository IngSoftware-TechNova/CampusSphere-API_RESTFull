package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Horario;
import com.technova.campussphereapi.service.ScheduleService;
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
@RequestMapping("/admin/horario")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Horario>> findAll() {
        List<Horario> horarios = scheduleService.findAll();
        return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Horario>> paginate(
            @PageableDefault(size = 5, sort = "hora_inicio")Pageable pageable) {
        Page<Horario> horarios = scheduleService.paginate(pageable);
        return new ResponseEntity<Page<Horario>>(horarios, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Horario> findById(@PathVariable("id") Integer id) {
        Horario horario = scheduleService.findById(id);
        return new ResponseEntity<Horario>(horario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Horario> create(@RequestBody Horario horario) {
        Horario newHorario = scheduleService.create(horario);
        return new ResponseEntity<Horario>(newHorario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> update(@PathVariable("id") Integer id,
                                          @RequestBody Horario horario) {
        Horario updateHorario = scheduleService.update(id,horario);
        return new ResponseEntity<Horario>(updateHorario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Horario> delete(@PathVariable("id") Integer id) {
        scheduleService.delete(id);
        return new ResponseEntity<Horario>(HttpStatus.NO_CONTENT);
    }

}
