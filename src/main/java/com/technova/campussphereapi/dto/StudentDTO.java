package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {

    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "La carrera es obligatoria")
    private String career;

}
