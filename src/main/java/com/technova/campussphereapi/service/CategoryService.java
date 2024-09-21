package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    Page<CategoryDTO> paginate(Pageable pageable);
    CategoryDTO findById(Integer id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(Integer id, CategoryDTO updatedCategoryDTO);
    void delete(Integer id);
}
