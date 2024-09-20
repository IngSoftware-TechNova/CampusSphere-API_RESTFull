package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComentarioService {
    List<Comentario> getAll();
    Page<Comentario> paginate(Pageable pageable);

    Comentario findById(Integer id);

    Comentario create(Comentario comentario);
    Comentario update(Integer id, Comentario updateCategory);
    void delete(Integer id);
}