package com.technova.campussphereapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PriceDTO {
    private Integer id;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", message = "El valor mínimo es de 0 soles")
    private BigDecimal price;

    @NotNull(message = "La descripción es obligatoria")
    @NotBlank(message = "La descripcion es obligatorio")
    @Size(max = 100, message = "La descripcion debe tener 100 caracteres o menos")
    private String description;

}
