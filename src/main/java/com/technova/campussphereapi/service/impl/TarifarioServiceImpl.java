package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Tarifario;
import com.technova.campussphereapi.repository.TarifarioRepository;
import com.technova.campussphereapi.service.TarifarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TarifarioServiceImpl implements TarifarioService {
    private final TarifarioRepository tarifarioRepository;



    @Transactional(readOnly = true)
    @Override
    public List<Tarifario> getAll() {
        return tarifarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Tarifario> paginate(Pageable pageable) {
        return tarifarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Tarifario findById(Integer id) {
        return tarifarioRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Tarifa no encontrada"));
    }

    @Transactional
    @Override
    public Tarifario create(Tarifario tarifario) {
        return tarifarioRepository.save(tarifario);
    }

    @Transactional
    @Override
    public Tarifario update(Integer id, Tarifario updateTarifario) {
        Tarifario tarifaDeDB = findById(id);
        tarifaDeDB.setPrecio(updateTarifario.getPrecio());
        tarifaDeDB.setDescripcion(updateTarifario.getDescripcion());
        return tarifarioRepository.save(tarifaDeDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Tarifario tarifa = findById(id);
        tarifarioRepository.delete(tarifa);
    }
}
