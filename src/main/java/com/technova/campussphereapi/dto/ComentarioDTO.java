package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.model.entity.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComentarioDTO {
    private Integer id;

    @NotBlank(message = "El texto es obligatorio")
    @Size(min = 1, max = 500, message = "El texto debe tener entre 1 y 500 caracteres")

    private String comentario;

    private Evento evento;

    private Estudiante estudiante;

}
