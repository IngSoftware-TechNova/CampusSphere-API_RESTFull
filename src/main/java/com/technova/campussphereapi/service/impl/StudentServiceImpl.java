package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.StudentDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventMapper;
import com.technova.campussphereapi.mapper.StudentMapper;
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.repository.StudentRepository;
import com.technova.campussphereapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final EventMapper eventMapper;
    private final StudentMapper studentMapper;

    @Transactional(readOnly = true)
    @Override
    public StudentDTO findById(Integer id) {

        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));

        return studentMapper.toDTO(student);

    }
}
