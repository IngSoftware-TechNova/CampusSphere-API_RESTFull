package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Horario;

import com.technova.campussphereapi.repository.ScheduleRepository;
import com.technova.campussphereapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Horario> findAll() {
        return scheduleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Horario> paginate(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Horario findById(Integer id) {
        return scheduleRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Horario no encontrado"));
    }

    @Transactional
    @Override
    public Horario create(Horario horario) {

        return scheduleRepository.save(horario);
    }

    @Transactional
    @Override
    public Horario update(Integer id, Horario updateHorario) {
        Horario horarioDeDB = findById(id);
        horarioDeDB.setHoraInicio(updateHorario.getHoraInicio());
        horarioDeDB.setHoraFin(updateHorario.getHoraFin());
        return scheduleRepository.save(horarioDeDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Horario horario = findById(id);
        scheduleRepository.delete(horario);
    }
}
