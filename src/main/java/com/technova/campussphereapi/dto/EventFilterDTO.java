package com.technova.campussphereapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterDTO {
    private Float precioMin;
    private Float precioMax;
    private String categoriaName;
    private String ubicacion;
}
