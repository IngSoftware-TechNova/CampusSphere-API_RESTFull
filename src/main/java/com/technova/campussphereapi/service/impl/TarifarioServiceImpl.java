package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Tarifario;
import com.technova.campussphereapi.repository.TarifarioRepository;
import com.technova.campussphereapi.service.TarifarioService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TarifarioServiceImpl implements TarifarioService {

    private final TarifarioRepository tarifarioRepository;

    @Transactional(readOnly = true)
    @Override
    public Tarifario findById(Integer id) {
        return tarifarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarifario not found"));
    }
}
