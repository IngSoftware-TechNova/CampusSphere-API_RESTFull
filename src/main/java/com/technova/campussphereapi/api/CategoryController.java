package com.technova.campussphereapi.api;


import com.technova.campussphereapi.model.entity.Category;
import com.technova.campussphereapi.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Category>> paginate(
            @PageableDefault(size = 5, sort ="name") Pageable pageable) {
        Page<Category> categorias = categoryService.paginate(pageable);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> search(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category newCategory = categoryService.create(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(id, category);
        return new ResponseEntity<Category>(updatedCategory,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
