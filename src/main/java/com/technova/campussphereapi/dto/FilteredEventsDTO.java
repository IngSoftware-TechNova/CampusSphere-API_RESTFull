package com.technova.campussphereapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilteredEventsDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer capacity;
    private String locationName;
    private String categoryName;
    private BigDecimal PriceValue;
}
