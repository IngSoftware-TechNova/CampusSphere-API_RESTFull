package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Horario;

import com.technova.campussphereapi.repository.HorarioRepository;
import com.technova.campussphereapi.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class HorarioServiceImpl implements HorarioService {
    private final HorarioRepository horarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Horario> getAll() {
        return horarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Horario> paginate(Pageable pageable) {
        return horarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Horario findById(Integer id) {
        return horarioRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Horario no encontrado"));
    }

    @Transactional
    @Override
    public Horario create(Horario horario) {

        return horarioRepository.save(horario);
    }

    @Transactional
    @Override
    public Horario update(Integer id, Horario updateHorario) {
        Horario horarioDeDB = findById(id);
        horarioDeDB.setHoraInicio(updateHorario.getHoraInicio());
        horarioDeDB.setHoraFin(updateHorario.getHoraFin());
        return horarioRepository.save(horarioDeDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Horario horario = findById(id);
        horarioRepository.delete(horario);
    }
}
