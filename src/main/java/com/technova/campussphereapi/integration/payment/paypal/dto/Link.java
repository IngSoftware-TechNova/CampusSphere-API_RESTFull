package com.technova.campussphereapi.integration.payment.paypal.dto;

import lombok.Data;

@Data
public class Link {
    private String href;
    private String rel;
    private String method;
}