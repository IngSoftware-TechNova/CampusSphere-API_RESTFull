package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaService {
    List<Categoria> getAll();
    Page<Categoria> paginate(Pageable pageable);
    Categoria getById(Integer id);
    Categoria create(Categoria categoria);
    Categoria update(Integer id, Categoria updatedCategoria);
    void delete(Integer id);

}
