package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.CategoryDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.CategoryMapper;
import com.technova.campussphereapi.model.entity.Category;
import com.technova.campussphereapi.repository.CategoryRepository;
import com.technova.campussphereapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con ID " + id + " no fue encontrada."));
        return categoryMapper.toDTO(category);
    }
}