package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> paginate(Pageable pageable);
    Category findById(Integer id);
    Category create(Category category);
    Category update(Integer id, Category updatedCategory);
    void delete(Integer id);
}
