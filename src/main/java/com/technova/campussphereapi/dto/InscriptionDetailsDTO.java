package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.enums.InscriptionStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InscriptionDetailsDTO {

    private Integer id;
    private InscriptionStatus inscriptionStatus;
    //private String nameEvent;
    private String studentName;
    private Float total;
    private LocalDateTime createdAt;
    private List<InscriptionItemDTO> items;

}
