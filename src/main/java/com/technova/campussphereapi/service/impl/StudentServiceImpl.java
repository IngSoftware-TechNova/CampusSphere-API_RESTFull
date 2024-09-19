package com.technova.campussphereapi.service.impl;

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

    @Transactional(readOnly = true)
    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante not found"));
    }
}
