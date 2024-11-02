package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventDetailsDTO {

    private Integer id;
    private String name;
    private Integer capacity;
    private String description;
    private String categoryName;
    private String locationName;
    private BigDecimal PriceValue;
}

