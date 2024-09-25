package com.technova.campussphereapi.dto;

import lombok.Data;

@Data
public class StudentProfileDTO {
    private Integer id;
    private String email;

    // Campos específicos para Customer
    private String name;  // Nombre del cliente o autor
    private String career;   // Apellido del cliente o autor
}
