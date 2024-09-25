package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleDTO {
    private Integer id;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime startHour;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime endHour;

    @NotNull(message = "La descripción es obligatoria")
    @NotBlank(message = "La descripcion es obligatorio")
    @Size(max = 200, message = "La descripcion debe tener 200 caracteres o menos")
    private String description;
}
