package com.technova.campussphereapi.api;
import com.technova.campussphereapi.dto.ComentarioDTO;
import com.technova.campussphereapi.dto.ComentarioReportDTO;
import com.technova.campussphereapi.service.ComentarioService;
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
@RequestMapping("/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getAllComentario(){
        List<ComentarioDTO> comentarios = comentarioService.getAll();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ComentarioDTO>> paginateComentarios(
            @PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<ComentarioDTO> comentario = comentarioService.paginate(pageable);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<List<ComentarioReportDTO>> getComentarioReport(){
        List<ComentarioReportDTO> reports = comentarioService.getComentarioReportByDate();
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getById(@PathVariable Integer id){
        ComentarioDTO comentario = comentarioService.findById(id);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> create(@Valid @RequestBody ComentarioDTO comentarioDTO){
        ComentarioDTO createdComentario = comentarioService.create(comentarioDTO);
        return new ResponseEntity<>(createdComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> update(@PathVariable Integer id,@Valid @RequestBody ComentarioDTO comentarioDTO){
        ComentarioDTO updateComentario = comentarioService.update(id, comentarioDTO);
        return new ResponseEntity<>(updateComentario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id){
        comentarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
