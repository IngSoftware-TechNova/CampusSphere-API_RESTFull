package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.EventCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.model.entity.Event;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public EventDetailsDTO toDetailsDTO(Event event) {
        EventDetailsDTO eventDetailsDTO = modelMapper.map(event, EventDetailsDTO.class);

        eventDetailsDTO.setCategoryName(event.getCategory().getName());
        eventDetailsDTO.setLocationName(event.getLocation().getLocation());
        eventDetailsDTO.setPriceValue(event.getPrice().getPrice());

        return eventDetailsDTO;
    }

    public Event toEntity(EventCreateUpdateDTO eventCreateUpdateDTO) {
        return modelMapper.map(eventCreateUpdateDTO, Event.class);
    }

    public EventCreateUpdateDTO toCreateUpdateDTO(Event event) {
        return modelMapper.map(event, EventCreateUpdateDTO.class);
    }
}
