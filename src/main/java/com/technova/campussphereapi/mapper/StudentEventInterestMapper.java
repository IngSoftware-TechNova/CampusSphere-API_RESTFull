package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.model.entity.StudentEventInterest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentEventInterestMapper {
    private final ModelMapper modelMapper;
    public StudentEventInterestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentEventInterestDTO toDTO(StudentEventInterest studentEventInterest) {
        return modelMapper.map(studentEventInterest, StudentEventInterestDTO.class);
    }

    public StudentEventInterest toEntity(StudentEventInterestDTO studentEventInterestDTO) {
        return modelMapper.map(studentEventInterestDTO, StudentEventInterest.class);
    }
}
