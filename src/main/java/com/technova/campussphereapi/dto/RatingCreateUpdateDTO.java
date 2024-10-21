package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingCreateUpdateDTO {

    private Integer id;

    @NotNull(message = "La calificacion es obligatorio")
    @Min(value = 1, message = "La puntuación debe ser al menos 1")
    @Max(value = 5, message = "La puntuación no puede ser mayor a 5")
    private Integer rate;

    private LocalDateTime ratingDate;

    private LocalDateTime updateDate;

    @NotNull(message = "El evento es obligatorio")
    private Integer eventId;

    @NotNull(message = "El estudiante es obligatorio")
    private Integer studentId;
}
