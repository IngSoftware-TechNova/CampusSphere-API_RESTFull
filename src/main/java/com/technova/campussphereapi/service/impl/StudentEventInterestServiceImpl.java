package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.StudentEventInterestDTO;
import com.technova.campussphereapi.dto.StudentEventInterestRequestDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.StudentEventInterestMapper;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.StudentEventInterest;
import com.technova.campussphereapi.model.entity.User;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.StudentEventInterestRepository;
import com.technova.campussphereapi.repository.UserRepository;
import com.technova.campussphereapi.service.StudentEventInterestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentEventInterestServiceImpl implements StudentEventInterestService {

    private final StudentEventInterestRepository studentEventInterestRepository;
    private final StudentEventInterestMapper studentEventInterestMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public StudentEventInterestServiceImpl(
            StudentEventInterestRepository studentEventInterestRepository,
            StudentEventInterestMapper studentEventInterestMapper,
            UserRepository userRepository,
            EventRepository eventRepository) {
        this.studentEventInterestRepository = studentEventInterestRepository;
        this.studentEventInterestMapper = studentEventInterestMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public StudentEventInterestDTO create(StudentEventInterestRequestDTO studentEventInterestRequestDTO) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResourceNotFoundException("Usuario no autenticado");
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Integer studentId = user.getStudent().getId();
        Integer eventId = studentEventInterestRequestDTO.getEventId();

        // Validar que el evento exista
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

        // Verificar si el interés ya existe para evitar duplicados
        boolean exists = studentEventInterestRepository.existsByStudentIdAndEventId(studentId, eventId);
        if (exists) {
            throw new IllegalStateException("Ya está registrado en este evento.");
        }

        // Mapear el DTO a la entidad
        StudentEventInterest studentEventInterest = studentEventInterestMapper.toEntity(studentEventInterestRequestDTO);

        // Asignar el evento y el estudiante autenticado
        studentEventInterest.setEvent(event);
        studentEventInterest.setStudent(user.getStudent());

        // Guardar en el repositorio
        StudentEventInterest savedStudentEventInterest = studentEventInterestRepository.save(studentEventInterest);

        // Retornar el DTO completo
        return studentEventInterestMapper.toDTO(savedStudentEventInterest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentEventInterestDTO> findByStudentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResourceNotFoundException("Usuario no autenticado");
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Integer studentAuthenticatedUserId = user.getStudent().getId();

        return studentEventInterestRepository.findAll().stream()
                .filter(interest -> interest.getStudent().getId().equals(studentAuthenticatedUserId))
                .map(studentEventInterestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentEventInterestDTO> findByEventId(Integer eventId) {
        return studentEventInterestRepository.findAll().stream()
                .filter(interest -> interest.getEvent().getId().equals(eventId))
                .map(studentEventInterestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteByEventId(Integer eventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResourceNotFoundException("Usuario no autenticado");
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Integer studentId = user.getStudent().getId();
        studentEventInterestRepository.deleteByStudentIdAndEventId(studentId, eventId);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isEventFavorite(Integer eventId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new ResourceNotFoundException("Usuario no autenticado");
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Integer studentId = user.getStudent().getId();

        return studentEventInterestRepository.existsByStudentIdAndEventId(studentId, eventId);
    }
}
