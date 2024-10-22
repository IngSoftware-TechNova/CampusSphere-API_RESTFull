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
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.model.entity.Inscription;
import com.technova.campussphereapi.model.entity.User;
import com.technova.campussphereapi.model.enums.InscriptionStatus;
import com.technova.campussphereapi.repository.StudentRepository;
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

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final InscriptionMapper inscriptionMapper;
    private final EventMapper eventMapper;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO) throws MessagingException {
        // Convertir el DTO en una entidad Inscription
        Inscription inscription = inscriptionMapper.toEntity(inscriptionCreateUpdateDTO);

        // Verificar si el estudiante existe en la base de datos
        User user = userRepository.findById(inscriptionCreateUpdateDTO.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " + inscriptionCreateUpdateDTO.getUserId()));

        // Asigna los atributos necesarios antes de guardar
        Event event = eventRepository.findById(inscriptionCreateUpdateDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento not found with id: " + inscriptionCreateUpdateDTO.getEventId()));

        inscriptionRepository.findByEventAndUser(event, user)
                .ifPresent(inscription1 -> {
                    throw new BadRequestException("Ya esta inscrito");
                });

        inscription.setEvent(event);
        inscription.setUser(user);
        inscription.setInscriptionDate(LocalDateTime.now());
        inscription.setInscriptionStatus(InscriptionStatus.PENDING);

        // Guardar la inscripcion
        Inscription savedInscription = inscriptionRepository.save(inscription);

        // Convertimos el evento a su dto
        EventDetailsDTO eventDetailsDTO = eventMapper.toDetailsDTO(event);

        // Enviamos el email de confirmacion
        sendInscriptionConfirmationEmail(eventDetailsDTO);

        // Retornar el DTO mapeado
        return inscriptionMapper.toDetailsDTO(savedInscription);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InscriptionReportDTO> getInscriptionPerEventReport() {

        List<Object[]> results = inscriptionRepository.getInscriptionPerEventReport();

        List<InscriptionReportDTO> inscriptionReportDTOS = results.stream()
                .map(result ->
                        new InscriptionReportDTO (
                                (String) result[0],
                                ((Integer) result[1]).intValue()
                        )
                ).toList();


        return inscriptionReportDTOS;
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
