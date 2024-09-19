package com.technova.campussphereapi.service.impl;

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

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;

    @Transactional
    @Override
    public Inscription create(Inscription inscription) {
        // Asigna los atributos necesarios antes de guardar
        Event event = eventRepository.findById(inscription.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Evento not found with id: " + inscription.getEvent().getId()));

        Student student = studentRepository.findById(inscription.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante not found with id: " + inscription.getStudent().getId()));

        inscription.setEvent(event);
        inscription.setStudent(student);
        inscription.setInscriptionDate(LocalDateTime.now());
        inscription.setInscriptionStatus(InscriptionStatus.PENDING);

        return inscriptionRepository.save(inscription);
    }

}
