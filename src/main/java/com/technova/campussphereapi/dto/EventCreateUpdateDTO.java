package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventCreateUpdateDTO {

    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotNull(message = "La capacidad es obligatoria")
    private Integer capacity;
    @NotBlank(message = "La descripcion es obligatoria")
    private String description;

    @NotNull(message = "La categoria es obligatoria")
    private Integer categoryId;
    @NotNull(message = "La locacion es obligatoria")
    private Integer locationId;
    @NotNull(message = "El precio es obligatorio")
    private Integer priceId;

}
