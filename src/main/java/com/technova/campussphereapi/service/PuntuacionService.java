package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Puntuacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PuntuacionService {
    List<Puntuacion> getAll();
    Page<Puntuacion> paginate(Pageable pageable);

    Puntuacion findById(Integer id);

    Puntuacion create(Puntuacion puntuacion);
    Puntuacion update(Integer id, Puntuacion updateCategory);
    void delete(Integer id);
}