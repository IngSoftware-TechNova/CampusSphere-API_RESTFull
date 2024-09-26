package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.ScheduleDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.ScheduleMapper;
import com.technova.campussphereapi.model.entity.Schedule;

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
    private final ScheduleMapper scheduleMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(scheduleMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ScheduleDTO> paginate(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(scheduleMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleDTO findById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El horario con ID " + id + " no fue encontrado"));
        return scheduleMapper.toDTO(schedule);
    }

    @Transactional
    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        //Verifica si ya existe un horario con la misma descripcion
        scheduleRepository.findByDescription(scheduleDTO.getDescription())
                .ifPresent(existingSchedule -> {
                    throw new BadRequestException("El horario ya existe con la misma descripcion");
                });

        // Verificar si la hora de fin es menor que la hora de inicio
        if (scheduleDTO.getEndHour().isBefore(scheduleDTO.getStartHour())) {
            throw new BadRequestException("La hora de inicio debe ser menor que la hora de fin");
        }

        // Verifica si existe un horario que se solape con las horas de inicio y fin
        scheduleRepository.findByStartHourAndEndHour(scheduleDTO.getStartHour(), scheduleDTO.getEndHour())
                .ifPresent(existingSchedule -> {
                    throw new BadRequestException("El horario se solapa con otro existente");
                });

        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDTO(schedule);
    }

    @Transactional
    @Override
    public ScheduleDTO update(Integer id, ScheduleDTO updateScheduleDTO) {
        Schedule scheduleFromDB = scheduleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El horario con ID " + id + " no fue encontrado"));

        //Verificar si ya existe otro horario con la misma descripcion
        scheduleRepository.findByDescription(updateScheduleDTO.getDescription())
                .filter(existingSchedule -> !existingSchedule.getId().equals(id))
                .ifPresent(existingSchedule -> {
                    throw new BadRequestException("El horario ya existe con la misma descripcion");
                });

        // Verificar si la hora de fin es menor que la hora de inicio
        if (updateScheduleDTO.getEndHour().isBefore(updateScheduleDTO.getStartHour())) {
            throw new BadRequestException("La hora de inicio debe ser menor que la hora de fin");
        }

        // Verificar si existe un horario que se solape con las horas de inicio y fin
        scheduleRepository.findByStartHourAndEndHour(updateScheduleDTO.getStartHour(), updateScheduleDTO.getEndHour())
                .filter(existingSchedule -> !existingSchedule.getId().equals(id))
                .ifPresent(existingSchedule -> {
                    throw new BadRequestException("El horario se solapa con otro existente");
                });

        scheduleFromDB.setStartHour(updateScheduleDTO.getStartHour());
        scheduleFromDB.setEndHour(updateScheduleDTO.getEndHour());
        scheduleFromDB.setDescription(updateScheduleDTO.getDescription());

        scheduleFromDB = scheduleRepository.save(scheduleFromDB);
        return scheduleMapper.toDTO(scheduleFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El horario con ID " + id + " no fue encontrado"));
        scheduleRepository.delete(schedule);
    }
}
