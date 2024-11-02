package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.LocationDTO;
import com.technova.campussphereapi.model.entity.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    private final ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LocationDTO toDTO(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }

    public Location toEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
    }
}
