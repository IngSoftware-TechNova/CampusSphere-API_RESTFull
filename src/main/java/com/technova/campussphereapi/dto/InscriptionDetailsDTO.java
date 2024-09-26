package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.enums.InscriptionStatus;
import lombok.Data;

@Data
public class InscriptionDetailsDTO {

    private Integer id;
    private InscriptionStatus inscriptionStatus;

    private String eventName;
    private String studentName;

}
