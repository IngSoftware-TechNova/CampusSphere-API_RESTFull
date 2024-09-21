package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.CategoryDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoryDTO> paginate(org.springframework.data.domain.Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDTO);
    }

    @Transactional
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        //Find if the category alread existed
        categoryRepository.findByName(categoryDTO.getName())
                        .ifPresent(existingCategory -> {
                            throw new BadRequestException("Category name already exists");
                        });
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The category with ID "+id+" not was found"));
        return categoryMapper.toDTO(category);
    }

    @Transactional
    @Override
    public CategoryDTO update(Integer id, CategoryDTO updatedCategoryDTO) {
        Category categoryFromDBb = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The category with ID "+id+" not found")
        );
        // verificate is the category is already existed
        categoryRepository.findByName(updatedCategoryDTO.getName())
                .filter(existingCategory -> !existingCategory.getId().equals(id))
                .ifPresent(existingCategory -> {
                    throw new BadRequestException("Category name already exists");
                });
        categoryFromDBb.setName(updatedCategoryDTO.getName());
        categoryFromDBb = categoryRepository.save(categoryFromDBb);
        return categoryMapper.toDTO(categoryFromDBb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Category categoryFromDBb = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("The category with ID "+id+" not found"));
        categoryRepository.delete(categoryFromDBb);
    }
}
