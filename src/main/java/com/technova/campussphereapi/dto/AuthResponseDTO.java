package com.technova.campussphereapi.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;         //El id del user
    private String token;       //El token JWT
    private String firstName;   //El primer nombre del usuario
    private String lastName;    //El apellido del usuario
    private String role;        //El rol del usuario
    private Integer studentId;  //El id del estudiante
}
