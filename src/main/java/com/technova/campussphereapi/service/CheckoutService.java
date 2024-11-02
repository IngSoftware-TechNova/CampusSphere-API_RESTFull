package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.PaymentCaptureResponse;
import com.technova.campussphereapi.dto.PaymentOrderResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer inscriptionId, String returnUrl, String cancelUrl) throws MessagingException;

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}
