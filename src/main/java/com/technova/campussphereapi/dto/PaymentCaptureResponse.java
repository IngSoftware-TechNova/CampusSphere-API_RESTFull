package com.technova.campussphereapi.dto;

import lombok.Data;

@Data
public class PaymentCaptureResponse {
    private boolean completed;
    private Integer inscriptionId;
}
