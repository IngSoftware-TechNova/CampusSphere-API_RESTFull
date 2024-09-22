package com.technova.campussphereapi.api;
import com.technova.campussphereapi.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.technova.campussphereapi.model.entity.Comentario;
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
    public ResponseEntity<List<Comentario>> getAllComentario(){
        List<Comentario> comentario = comentarioService.getAll();
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Comentario>> paginateComentarios(
            @PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<Comentario> comentario = comentarioService.paginate(pageable);
        return new ResponseEntity<Page<Comentario>>(comentario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable("id") Integer id){
        Comentario comentario = comentarioService.findById(id);
        return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario){
        Comentario newComentario = comentarioService.create(comentario);
        return new ResponseEntity<>(newComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable("id") Integer id, @RequestBody Comentario comentario){
        Comentario updateComentario = comentarioService.update(id, comentario);
        return new ResponseEntity<>(updateComentario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable("id") Integer id){
        comentarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
