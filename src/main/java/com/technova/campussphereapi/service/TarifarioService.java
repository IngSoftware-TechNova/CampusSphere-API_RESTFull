package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Tarifario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TarifarioService {
    List<Tarifario> getAll();
    Page<Tarifario> paginate(Pageable pageable);
    Tarifario findById(Integer id);
    Tarifario create(Tarifario tarifario);
    Tarifario update(Integer id, Tarifario updateTarifario);
    void delete(Integer id);
}
