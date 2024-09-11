package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.model.entity.Evento;
import com.technova.campussphereapi.model.entity.Inscripcion;
import com.technova.campussphereapi.model.enums.InscripcionStatus;
import com.technova.campussphereapi.repository.EstudianteRepository;
import com.technova.campussphereapi.repository.EventoRepository;
import com.technova.campussphereapi.repository.InscripcionRepository;
import com.technova.campussphereapi.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EventoRepository eventoRepository;
    private final EstudianteRepository estudianteRepository;

    @Transactional
    @Override
    public Inscripcion create(Inscripcion inscripcion) {
        // Asigna los atributos necesarios antes de guardar
        Evento evento = eventoRepository.findById(inscripcion.getEvento().getId())
                .orElseThrow(() -> new RuntimeException("Evento not found with id: " + inscripcion.getEvento().getId()));

        Estudiante estudiante = estudianteRepository.findById(inscripcion.getEstudiantes().getId())
                .orElseThrow(() -> new RuntimeException("Estudiante not found with id: " + inscripcion.getEstudiantes().getId()));

        inscripcion.setEvento(evento);
        inscripcion.setEstudiantes(estudiante);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setInscripcionStatus(InscripcionStatus.PENDING);

        return inscripcionRepository.save(inscripcion);
    }

}
