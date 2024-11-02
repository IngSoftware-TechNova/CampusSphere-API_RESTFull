package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.dto.StudentEventInterestRequestDTO;
import com.technova.campussphereapi.model.entity.StudentEventInterest;

import java.util.List;

public interface StudentEventInterestService {
    StudentEventInterestDTO create(StudentEventInterestRequestDTO studentEventInterestRequestDTO);
    List<StudentEventInterestDTO> findByStudentId();
    List<StudentEventInterestDTO> findByEventId(Integer eventId);
    void deleteByEventId(Integer eventId);
}
