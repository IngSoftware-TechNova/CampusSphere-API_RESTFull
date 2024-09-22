package com.technova.campussphereapi.service.impl;


import com.technova.campussphereapi.model.entity.Puntuacion;
import com.technova.campussphereapi.repository.PuntuacionRepository;
import com.technova.campussphereapi.service.PuntuacionService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PuntuacionServiceImpl implements PuntuacionService {
    private final PuntuacionRepository puntuacionRepository;

    @Override
    public List<Puntuacion> getAll() {
        return puntuacionRepository.findAll();
    }

    @Override
    public Page<Puntuacion> paginate(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Puntuacion findById(Integer id) {
        return puntuacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Puntuacion not found"));
    }

    @Transactional
    @Override
    public Puntuacion create(Puntuacion puntuacion) {
        return puntuacionRepository.save(puntuacion);
    }

    @Transactional
    @Override
    public Puntuacion update(Integer id, Puntuacion updateCategory) {
        Puntuacion puntuacionFromDb = findById(id);
        return puntuacionRepository.save(puntuacionFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Puntuacion puntuacion = findById(id);
        puntuacionRepository.delete(puntuacion);
    }
}
