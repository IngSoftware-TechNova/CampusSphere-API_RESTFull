package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.dto.StudentEventInterestRequestDTO;
import com.technova.campussphereapi.model.entity.StudentEventInterest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentEventInterestMapper {

    private final ModelMapper modelMapper;

    public StudentEventInterestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Configuración para mapear los detalles esenciales de `Event` en `StudentEventInterestDTO`
        modelMapper.typeMap(StudentEventInterest.class, StudentEventInterestDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getStudent().getId(), StudentEventInterestDTO::setStudentId);
            mapper.map(src -> src.getEvent().getId(), StudentEventInterestDTO::setEventId);
            mapper.map(src -> src.getEvent().getName(), StudentEventInterestDTO::setEventName);
            mapper.map(src -> src.getEvent().getCreatedAt(), StudentEventInterestDTO::setEventCreatedAt);
            mapper.map(src -> src.getEvent().getLocation().getLocation(), StudentEventInterestDTO::setEventLocationName);
            mapper.map(src -> src.getEvent().getPrice().getPrice(), StudentEventInterestDTO::setEventPriceAmount);
        });
    }

    // Método para convertir de entidad a DTO
    public StudentEventInterestDTO toDTO(StudentEventInterest studentEventInterest) {
        return modelMapper.map(studentEventInterest, StudentEventInterestDTO.class);
    }

    // Método para convertir de DTO de request a entidad
    public StudentEventInterest toEntity(StudentEventInterestRequestDTO studentEventInterestRequestDTO) {
        StudentEventInterest studentEventInterest = new StudentEventInterest();
        modelMapper.map(studentEventInterestRequestDTO, studentEventInterest);
        return studentEventInterest;
    }
}
