package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.model.entity.StudentEventInterest;

import java.util.List;

public interface StudentEventInterestService {
    StudentEventInterestDTO create(StudentEventInterestDTO studentEventInterestDTO);
    List<StudentEventInterestDTO> findByStudentId(Integer studentId);
    List<StudentEventInterestDTO> findByEventId(Integer eventId);
}
