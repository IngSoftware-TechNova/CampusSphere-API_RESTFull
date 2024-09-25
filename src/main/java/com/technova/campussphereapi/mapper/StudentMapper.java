package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.StudentProfileDTO;
import com.technova.campussphereapi.dto.StudentRegistrationDTO;
import com.technova.campussphereapi.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentRegistrationDTO toDTO(Student student) {
        return modelMapper.map(student, StudentRegistrationDTO.class);
    }

    public Student toEntity(StudentRegistrationDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    public StudentProfileDTO toDTOs(Student student) {
        return modelMapper.map(student, StudentProfileDTO.class);
    }


}
