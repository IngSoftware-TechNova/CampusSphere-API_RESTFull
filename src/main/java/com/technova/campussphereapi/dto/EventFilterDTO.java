package com.technova.campussphereapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterDTO {
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private String categoriaName;
    private String ubicacion;
}
