package com.technova.campussphereapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
    private int id;

    @NotBlank(message = "The name is necessary")
    @Size(max = 50, message = "The name must be 50 characters or less")
    private String name;
}
