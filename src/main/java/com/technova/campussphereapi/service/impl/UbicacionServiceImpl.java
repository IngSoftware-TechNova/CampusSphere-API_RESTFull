package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Ubicacion;
import com.technova.campussphereapi.repository.UbicacionRepository;
import com.technova.campussphereapi.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    @Transactional(readOnly = true)
    @Override
    public Ubicacion findById(Integer id) {
        return ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicacion not found"));
    }
}
