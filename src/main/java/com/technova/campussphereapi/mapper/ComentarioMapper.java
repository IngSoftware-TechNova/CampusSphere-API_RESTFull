package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.ComentarioDTO;
import com.technova.campussphereapi.model.entity.Comentario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    private final ModelMapper modelMapper;

    public ComentarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ComentarioDTO toDTO(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioDTO.class);
    }

    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        return modelMapper.map(comentarioDTO, Comentario.class);
    }
}
