package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.AuthResponseDTO;
import com.technova.campussphereapi.dto.LoginDTO;
import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.model.entity.User;

public interface UserService {
    // Register a Student
    UserProfileDTO registerStudent(UserRegistrationDTO registrationDTO);

    // Si hubiera otro Role, agregaria más tipos de registration para cada uno de estos

    // Actualizar el perfil de usuario
    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);

    // Authenticate user(login)
    AuthResponseDTO login(LoginDTO loginDTO);

    // Get user profile by ID
    UserProfileDTO getUserProfileById(Integer id);
}
