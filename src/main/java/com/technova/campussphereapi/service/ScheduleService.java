package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.ScheduleDTO;
import com.technova.campussphereapi.model.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> findAll();
    Page<ScheduleDTO> paginate(Pageable pageable);
    ScheduleDTO findById(Integer id);
    ScheduleDTO create(ScheduleDTO scheduleDTO);
    ScheduleDTO update(Integer id, ScheduleDTO updateScheduleDTO);
    void delete(Integer id);
}
