package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.repository.CategoriaRepository;
import com.technova.campussphereapi.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
