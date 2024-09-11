package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Horario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HorarioService {
    List<Horario> getAll();
    Page<Horario> paginate(Pageable pageable);
    Horario findById(Integer id);
    Horario create(Horario Horario);
    Horario update(Integer id, Horario updateHorario);
    void delete(Integer id);
}
