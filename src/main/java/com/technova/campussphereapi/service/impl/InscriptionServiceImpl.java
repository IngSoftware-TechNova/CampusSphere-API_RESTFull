package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final InscriptionMapper inscriptionMapper;

    @Transactional
    @Override
    public InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO) {
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

}
