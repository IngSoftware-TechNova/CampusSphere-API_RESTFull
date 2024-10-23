package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.integration.notification.email.dto.Mail;
import com.technova.campussphereapi.integration.notification.email.service.EmailService;
import com.technova.campussphereapi.mapper.EventMapper;
import com.technova.campussphereapi.mapper.InscriptionMapper;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.model.entity.User;
import com.technova.campussphereapi.model.enums.InscriptionStatus;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.InscriptionRepository;
import com.technova.campussphereapi.repository.UserRepository;
import com.technova.campussphereapi.service.InscriptionService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InscriptionMapper inscriptionMapper;
    private final EventMapper eventMapper;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionDTO) throws MessagingException {
        // Convertir el DTO en una entidad Purchase
        Inscription inscription = inscriptionMapper.toInscriptionEntity(inscriptionDTO);

        // Verificar si el cliente existe en la base de datos
        /*User user = userRepository.findById(purchaseDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + purchaseDTO.getCustomerId()));*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }


        inscription.setUser(user); // Asociar el cliente a la compra

        // Verificar si los eventos existen en la base de datos antes de proceder
        inscription.getItems().forEach(item -> {
            Event event = eventRepository.findById(item.getEvent().getId())
                    .orElseThrow(() -> new RuntimeException("Event not found with ID: " + item.getEvent().getId()));
            item.setEvent(event);// Asociar el event existente al PurchaseItem
            item.setPrice(event.getPrice().getPrice().floatValue());
            item.setInscription(inscription); // Asociar el PurchaseItem a la inscription actual
        });



        // Establecer la fecha de creación y estado de pago
        inscription.setInscriptionDate(LocalDateTime.now());
        inscription.setInscriptionStatus(InscriptionStatus.PENDING);

        // Calcular el total basado en la cantidad de eventos comprados
        Float total = inscription.getItems()
                .stream()
                .map(item -> item.getPrice() * 1)
                .reduce(0f, Float::sum);

        inscription.setTotal(total);

        // Guardar la compra
        Inscription savedInscription = inscriptionRepository.save(inscription);

        // Convertimos el evento a su dto
        EventDetailsDTO eventDetailsDTO = eventMapper.toDetailsDTO(event);

        // Enviamos el email de confirmacion
        sendInscriptionConfirmationEmail(eventDetailsDTO);

        // Retornar el DTO mapeado
        return inscriptionMapper.toInscriptionDTO(savedInscription);
    }

    /*
    @Transactional(readOnly = true)
    @Override
    public List<InscriptionReportDTO> getInscriptionEventReportDate() {
        List<Object[]> results = inscriptionRepository.getInscriptionEventReportDate();

        // Mapea cada Object[] a un PurchaseReportDTO
        return results.stream().map(result ->
                new InscriptionReportDTO(
                        ((Integer) result[0]).intValue(),  // Cast de la cantidad
                        (String) result[1]                // Cast de la fecha
                )
        ).collect(Collectors.toList());
    }

     */

    @Transactional(readOnly = true)
    @Override
    public List<InscriptionDetailsDTO> getInscriptionHistoryByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;

        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser")) {
            user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(ResourceNotFoundException::new);
        }

        return inscriptionRepository.findByUserId(user.getId()).stream()
                .map(inscriptionMapper::toInscriptionDTO)
                .toList();
    }

    @Override
    public List<InscriptionDetailsDTO> getAllInscription() {
        return inscriptionRepository.findAll()
                .stream()
                .map(inscriptionMapper::toInscriptionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public InscriptionDetailsDTO confirmInscription(Integer inscriptionId) {
        // Obtener la entidad inscription directamente desde el repositorio

        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscription not found"));

        inscription.setInscriptionStatus(InscriptionStatus.PAID);

        // Guardar y retornar el DTO actualizado
        Inscription updatedPurchase = inscriptionRepository.save(inscription);
        return inscriptionMapper.toInscriptionDTO(updatedPurchase);
    }

    @Transactional(readOnly = true)
    @Override
    public InscriptionDetailsDTO getInscriptionById(Integer id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
        return inscriptionMapper.toInscriptionDTO(inscription);  // Retornar el DTO en lugar de la entidad
    }

    @Transactional
    @Override
    public void delete(Integer eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento not found with id: " + eventId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        Inscription inscription = inscriptionRepository.findByEventAndUser(event, user)
                        .orElseThrow(() -> new ResourceNotFoundException("Inscription not found with eventId: " + eventId + " and mail: " + userEmail));

        inscriptionRepository.delete(inscription);
    }

    private void sendInscriptionConfirmationEmail(EventDetailsDTO eventDetailsDTO) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Map<String, Object> model = new HashMap<>();
        model.put("user", userEmail);
        model.put("total", eventDetailsDTO.getPriceValue());
        model.put("eventUrl", "http://localhost:4200/inscription/" + eventDetailsDTO.getId());

        Mail mail = emailService.createMail(
                userEmail,
                "Confirmacion de Inscripcion",
                model,
                mailFrom
        );
        emailService.sendMail(mail, "email/inscription-confirmation-template");

    }

}
