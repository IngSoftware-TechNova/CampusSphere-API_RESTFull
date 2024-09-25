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
import com.technova.campussphereapi.model.enums.InscriptionStatus;
import com.technova.campussphereapi.repository.StudentRepository;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.InscriptionRepository;
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
    private final InscriptionMapper inscriptionMapper;

    @Transactional
    @Override
    public InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO) {

        //inscriptionRepository.findByEventAndStudent(inscriptionCreateUpdateDTO.getEventId(), inscriptionCreateUpdateDTO.getStudentId())
        //        .ifPresent(student -> {
        //            throw new BadRequestException("Ya esta inscrito");
        //        });

        // Asigna los atributos necesarios antes de guardar
        Event event = eventRepository.findById(inscriptionCreateUpdateDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento not found with id: " + inscriptionCreateUpdateDTO.getEventId()));

        Student student = studentRepository.findById(inscriptionCreateUpdateDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found with id: " + inscriptionCreateUpdateDTO.getStudentId()));

        Inscription inscription = inscriptionMapper.toEntity(inscriptionCreateUpdateDTO);

        inscription.setEvent(event);
        inscription.setStudent(student);
        inscription.setInscriptionDate(LocalDateTime.now());
        inscription.setInscriptionStatus(InscriptionStatus.PENDING);

        return inscriptionMapper.toDetailsDTO(inscriptionRepository.save(inscription));
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
