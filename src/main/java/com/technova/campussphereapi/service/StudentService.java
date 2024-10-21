package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.StudentProfileDTO;
import com.technova.campussphereapi.dto.StudentRegistrationDTO;

public interface StudentService {
   StudentRegistrationDTO create(StudentRegistrationDTO studentDTO);
   StudentProfileDTO findById(Integer id);
   StudentRegistrationDTO update(Integer id, StudentRegistrationDTO updateStudentDTO);
   void delete(Integer id);
}
