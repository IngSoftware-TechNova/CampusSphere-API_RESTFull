package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.PuntuacionDTO;
import com.technova.campussphereapi.model.entity.Puntuacion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PuntuacionMapper {

    private final ModelMapper modelMapper;

    public PuntuacionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PuntuacionDTO toDTO(Puntuacion puntuacion) {
        return modelMapper.map(puntuacion, PuntuacionDTO.class);
    }

    public Puntuacion toEntity(PuntuacionDTO puntuacionDTO) {
        return modelMapper.map(puntuacionDTO, Puntuacion.class);
    }
}