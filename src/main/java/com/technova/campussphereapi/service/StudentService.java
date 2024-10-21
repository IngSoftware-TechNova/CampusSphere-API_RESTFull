package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;

public interface StudentService {
   UserRegistrationDTO create(UserRegistrationDTO studentDTO);
   UserProfileDTO findById(Integer id);
   UserRegistrationDTO update(Integer id, UserRegistrationDTO updateStudentDTO);
   void delete(Integer id);
}
