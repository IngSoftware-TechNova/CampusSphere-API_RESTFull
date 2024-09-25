package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.StudentDTO;

public interface StudentService {
    StudentDTO findById(Integer id);
}
