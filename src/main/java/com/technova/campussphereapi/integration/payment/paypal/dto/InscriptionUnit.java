package com.technova.campussphereapi.integration.payment.paypal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InscriptionUnit {
    @JsonProperty("reference_id")
    private String referenceId;
    private Amount amount;
}