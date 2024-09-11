package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Evento;
import com.technova.campussphereapi.repository.EventoRepository;
import com.technova.campussphereapi.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    @Transactional(readOnly = true)
    @Override
    public Evento findById(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento not found"));
    }
}
