package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocationDTO {
    private Integer id;

    @NotBlank(message = "Direction is necesary")
    @Size(max = 200, message = "The Location must have 200 characters or less")
    private String location;

    @NotBlank(message = "City is necesary")
    @Size(max = 200, message = "The City must have 200 characters or less")
    private String city;

    @NotBlank(message = "Country is necesary")
    @Size(max = 50, message = "The Country must have 200 characters or less")
    private String country;
}
