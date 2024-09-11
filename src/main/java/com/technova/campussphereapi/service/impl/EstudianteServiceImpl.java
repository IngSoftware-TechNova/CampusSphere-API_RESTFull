package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.repository.EstudianteRepository;
import com.technova.campussphereapi.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Transactional(readOnly = true)
    @Override
    public Estudiante findById(Integer id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante not found"));
    }
}
