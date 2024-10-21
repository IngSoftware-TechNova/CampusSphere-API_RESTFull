package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.enums.ERole;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Integer id;
    private String email;
    private ERole role; // El rol puede ser STUDENT o otro rol
    private String firstName;  // Nombre del STUDENT o otro rol
    private String lastName;  // Apellido del STUDENT o otro rol

    // Campos específicos para STUDENT
    private String career;   // Carrera del STUDENT
}
