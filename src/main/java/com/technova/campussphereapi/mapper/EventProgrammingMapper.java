package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.EventProgrammingCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventProgrammingDTO;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.EventProgramming;
import com.technova.campussphereapi.model.entity.Schedule;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class EventProgrammingMapper {

    private final ModelMapper modelMapper;

    public EventProgrammingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public EventProgrammingDTO toDTO(EventProgramming eventProgramming) {
        EventProgrammingDTO dto = modelMapper.map(eventProgramming, EventProgrammingDTO.class);
        dto.setEventId(eventProgramming.getEvent().getId());
        dto.setScheduleId(eventProgramming.getSchedule().getId());
        return dto;
    }

    public EventProgramming toEntity(EventProgrammingCreateUpdateDTO dto) {
        return modelMapper.map(dto, EventProgramming.class);
    }
}
