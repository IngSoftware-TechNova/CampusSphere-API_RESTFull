package com.technova.campussphereapi.dto;

import lombok.Data;

@Data
public class InscriptionItemDTO {
    private Integer eventId;
    private Float price;
    private Integer quantity;
    private String nameEvent;
}
