package com.technova.campussphereapi.api;


import com.technova.campussphereapi.dto.CategoryDTO;
import com.technova.campussphereapi.model.entity.Category;
import com.technova.campussphereapi.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> list() {
        List<CategoryDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDTO>> paginate(
            @PageableDefault(size = 5, sort ="name") Pageable pageable) {
        Page<CategoryDTO> categories = categoryService.paginate(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> search( @PathVariable("id") Integer id) {
        CategoryDTO category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.create(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.update(id, categoryDTO);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
