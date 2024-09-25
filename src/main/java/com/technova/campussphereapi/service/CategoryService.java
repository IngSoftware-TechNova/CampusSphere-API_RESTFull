package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO findById(Integer id);
}
