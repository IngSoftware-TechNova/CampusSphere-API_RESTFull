package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.mapper.StudentEventInterestMapper;
import com.technova.campussphereapi.model.entity.StudentEventInterest;
import com.technova.campussphereapi.repository.StudentEventInterestRepository;
import com.technova.campussphereapi.service.StudentEventInterestService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentEventInterestServiceImpl implements StudentEventInterestService {
    private final StudentEventInterestRepository studentEventInterestRepository;
    private final StudentEventInterestMapper studentEventInterestMapper;
    public StudentEventInterestServiceImpl(StudentEventInterestRepository studentEventInterestRepository, StudentEventInterestMapper studentEventInterestMapper) {
        this.studentEventInterestRepository = studentEventInterestRepository;
        this.studentEventInterestMapper = studentEventInterestMapper;
    }

    @Override
    public StudentEventInterestDTO create(StudentEventInterestDTO studentEventInterestDTO) {
        StudentEventInterest studentEventInterest = studentEventInterestMapper.toEntity(studentEventInterestDTO);
        StudentEventInterest savedStudentEventInterest = studentEventInterestRepository.save(studentEventInterest);
        return studentEventInterestMapper.toDTO(savedStudentEventInterest);
    }

    @Override
    public List<StudentEventInterestDTO> findByStudentId(Integer studentId) {
            return studentEventInterestRepository.findAll().stream()
                    .filter(interest -> interest.getStudent().getId().equals(studentId))
                    .map(studentEventInterestMapper::toDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<StudentEventInterestDTO> findByEventId(Integer eventId) {
        return studentEventInterestRepository.findAll().stream()
                .filter(interest -> interest.getEvent().getId().equals(eventId))
                .map(studentEventInterestMapper::toDTO)
                .collect(Collectors.toList());
    }
}
