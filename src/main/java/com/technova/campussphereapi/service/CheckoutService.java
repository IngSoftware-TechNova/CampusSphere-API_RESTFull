package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.PaymentCaptureResponse;
import com.technova.campussphereapi.dto.PaymentOrderResponse;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer inscriptionId, String returnUrl, String cancelUrl) ;

    PaymentCaptureResponse capturePayment(String orderId) ;
}
