package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.config.ModelMapperConfig;
import com.technova.campussphereapi.dto.ScheduleDTO;
import com.technova.campussphereapi.model.entity.Schedule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    private final ModelMapper modelMapper;

    public ScheduleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDTO.class);
    }

    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        return modelMapper.map(scheduleDTO, Schedule.class);
    }
}
