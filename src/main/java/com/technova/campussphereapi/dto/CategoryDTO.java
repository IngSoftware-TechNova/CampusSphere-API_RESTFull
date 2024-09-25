package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private Integer id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

}