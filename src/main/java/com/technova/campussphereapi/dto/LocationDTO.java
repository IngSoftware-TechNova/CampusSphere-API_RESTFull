package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocationDTO {

    private Integer id;
    @NotBlank(message = "La locacion es obligatoria")
    private String location;
    @NotBlank(message = "La ciudad es obligatoria")
    private String city;
    @NotBlank(message = "El pais es obligatorio")
    private String country;

}
