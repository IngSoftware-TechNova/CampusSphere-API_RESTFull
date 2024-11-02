package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserRegistrationDTO toDTO(Student student) {
        return modelMapper.map(student, UserRegistrationDTO.class);
    }

    public Student toEntity(UserRegistrationDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    public UserProfileDTO toDTOs(Student student) {
        return modelMapper.map(student, UserProfileDTO.class);
    }
}
