package com.technova.campussphereapi.integration.payment.paypal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private String intent;

    @JsonProperty("purchase_units")
    private List<InscriptionUnit> inscriptionUnits;

    @JsonProperty("application_context")
    private ApplicationContext applicationContext;
}