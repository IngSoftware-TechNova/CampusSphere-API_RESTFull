package com.technova.campussphereapi.integration.payment.paypal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class OrderCaptureResponse {
    private String status;

    @JsonProperty("purchase_units")
    private List<InscriptionUnit> inscriptionUnits;
}
