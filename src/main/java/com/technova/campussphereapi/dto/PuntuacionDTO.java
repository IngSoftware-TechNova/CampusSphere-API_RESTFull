package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.model.entity.Evento;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PuntuacionDTO {
    private Integer id;

    @NotBlank(message = "La puntuación es obligatorio")
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación debe ser al menos 1")
    @Max(value = 5, message = "La puntuación no puede ser mayor a 5")
    private Integer puntuacion;

    private Evento evento;

    private Estudiante estudiante;

}