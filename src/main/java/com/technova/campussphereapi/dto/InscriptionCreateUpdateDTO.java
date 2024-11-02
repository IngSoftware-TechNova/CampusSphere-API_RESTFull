package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.enums.InscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InscriptionCreateUpdateDTO {
    private Integer id;
    private InscriptionStatus inscriptionStatus;
    private LocalDateTime inscriptionDate;

    //@NotNull(message = "El evento es obligatorio")
    //private Integer eventId;

    @NotNull(message = "El usuario es obligatorio")
    private Integer studentId;

    private Float total;
    private List<InscriptionItemCreateUpdateDTO> items;

}
