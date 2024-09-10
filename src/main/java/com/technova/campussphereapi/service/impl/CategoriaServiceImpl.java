package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.repository.CategoriaRepository;
import com.technova.campussphereapi.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Categoria> paginate(org.springframework.data.domain.Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    @Override
    public Categoria getById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Categoria update(Integer id, Categoria updatedCategoria) {
        Categoria categoriaDB = getById(id);
        categoriaDB.setNombre(updatedCategoria.getNombre());
        return categoriaRepository.save(categoriaDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Categoria categoriaDB = getById(id);
        categoriaRepository.delete(categoriaDB);
    }
}
