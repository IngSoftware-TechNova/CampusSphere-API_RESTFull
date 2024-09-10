package com.technova.campussphereapi.api;


import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listaCategorias() {
        List<Categoria> categories = categoriaService.getAll();
        return new ResponseEntity<List<Categoria>>(categories,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Categoria>> paginaCategorias(
            @PageableDefault(size = 5, sort ="nombre") Pageable pageable) {
        Page<Categoria> categorias = categoriaService.paginate(pageable);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscaCategoria(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.getById(id);
        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.create(categoria);
        return new ResponseEntity<Categoria>(newCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        Categoria updatedCategoria = categoriaService.update(id, categoria);
        return new ResponseEntity<Categoria>(updatedCategoria,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable("id") Integer id) {
        categoriaService.delete(id);
        return new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
    }
}
