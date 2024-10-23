package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.PaymentCaptureResponse;
import com.technova.campussphereapi.dto.PaymentOrderResponse;
import com.technova.campussphereapi.integration.payment.paypal.dto.OrderCaptureResponse;
import com.technova.campussphereapi.integration.payment.paypal.dto.OrderResponse;
import com.technova.campussphereapi.integration.payment.paypal.service.PaypalService;
import com.technova.campussphereapi.service.CheckoutService;
import com.technova.campussphereapi.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PaypalService payPalService;
    private final InscriptionService inscriptionService;

    @Override
    public PaymentOrderResponse createPayment(Integer inscriptionId, String returnUrl, String cancelUrl){
        OrderResponse orderResponse =payPalService.createOrder(inscriptionId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);

    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId){
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getInscriptionUnits().get(0).getReferenceId();
            InscriptionDetailsDTO inscriptionDetailsDTO = inscriptionService.confirmInscription(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setInscriptionId(inscriptionDetailsDTO.getId());

        }
        return paypalCaptureResponse;
    }
}
