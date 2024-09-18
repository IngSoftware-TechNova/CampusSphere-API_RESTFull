package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Category;
import com.technova.campussphereapi.repository.CategoryRepository;
import com.technova.campussphereapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> paginate(org.springframework.data.domain.Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Category update(Integer id, Category updatedCategory) {
        Category categoryDB = findById(id);
        categoryDB.setNombre(updatedCategory.getNombre());
        return categoryRepository.save(categoryDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Category categoryDB = findById(id);
        categoryRepository.delete(categoryDB);
    }
}
