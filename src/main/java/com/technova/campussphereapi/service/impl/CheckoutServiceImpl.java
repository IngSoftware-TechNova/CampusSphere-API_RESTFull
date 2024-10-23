package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.PaymentCaptureResponse;
import com.technova.campussphereapi.dto.PaymentOrderResponse;
import com.technova.campussphereapi.integration.notification.email.dto.Mail;
import com.technova.campussphereapi.integration.notification.email.service.EmailService;
import com.technova.campussphereapi.integration.payment.paypal.dto.OrderCaptureResponse;
import com.technova.campussphereapi.integration.payment.paypal.dto.OrderResponse;
import com.technova.campussphereapi.integration.payment.paypal.service.PaypalService;
import com.technova.campussphereapi.service.CheckoutService;
import com.technova.campussphereapi.service.InscriptionService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PaypalService payPalService;
    private final InscriptionService inscriptionService;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public PaymentOrderResponse createPayment(Integer inscriptionId, String returnUrl, String cancelUrl) throws MessagingException{
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
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException{
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getInscriptionUnits().get(0).getReferenceId();
            InscriptionDetailsDTO inscriptionDetailsDTO = inscriptionService.confirmInscription(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setInscriptionId(inscriptionDetailsDTO.getId());
            sendInscriptionConfirmationEmail(inscriptionDetailsDTO);
        }
        return paypalCaptureResponse;
    }

    private void sendInscriptionConfirmationEmail(InscriptionDetailsDTO inscriptionDetailsDTO) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Map<String, Object> model = new HashMap<>();
        model.put("user", userEmail);
        model.put("total", inscriptionDetailsDTO.getTotal());
        model.put("eventUrl", "http://localhost:4200/inscription/" + inscriptionDetailsDTO.getId());

        Mail mail = emailService.createMail(
                userEmail,
                "Confirmacion de Inscripcion",
                model,
                mailFrom
        );
        emailService.sendMail(mail, "email/inscription-confirmation-template");
    }
}
