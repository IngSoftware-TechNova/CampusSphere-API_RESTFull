package com.technova.campussphereapi.service.impl;


import com.technova.campussphereapi.dto.RatingCreateUpdateDTO;
import com.technova.campussphereapi.dto.RatingDetailsDTO;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.RatingMapper;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Rating;
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.RatingRepository;
import com.technova.campussphereapi.repository.StudentRepository;
import com.technova.campussphereapi.service.RatingService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PuntuacionReportDTO> getRateReportByDate() {
        List<Object[]> results = ratingRepository.getPuntuacionReportByDate();
        //Mapeo de la lista de objetos a una lista de PuntuacionDTO
        List<PuntuacionReportDTO> PuntuacionReportDTOS = results.stream()
                .map(result ->
                        new PuntuacionReportDTO (
                                ((Integer) result[0]).intValue(),
                                (String) result[1]
                        )
                ).toList();
        return PuntuacionReportDTOS;
    }

    @Override
    public List<RatingDetailsDTO> findAll() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(ratingMapper::toDetailsDTO)
                .toList();
    }

    @Override
    public RatingDetailsDTO findById(Integer id) {
        Rating rating = ratingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Calificacion no encontrado con id: " + id));
        return ratingMapper.toDetailsDTO(rating);
    }

    @Transactional
    @Override
    public RatingDetailsDTO create(RatingCreateUpdateDTO ratingCreateUpdateDTO) {

        ratingRepository.findByEventIdAndStudentId(ratingCreateUpdateDTO.getEventId(), ratingCreateUpdateDTO.getStudentId())
                .ifPresent(student -> {
                    throw new BadRequestException("Ya haz calificado");
                });

        Event event = eventRepository.findById(ratingCreateUpdateDTO.getEventId())
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id: " + ratingCreateUpdateDTO.getEventId()));

        Student student = studentRepository.findById(ratingCreateUpdateDTO.getStudentId())
                .orElseThrow(()->new ResourceNotFoundException("Student not found with id: " + ratingCreateUpdateDTO.getStudentId()));

        Rating rating = ratingMapper.toEntity(ratingCreateUpdateDTO);

        if (ratingCreateUpdateDTO.getRate() < 1 || ratingCreateUpdateDTO.getRate() > 5) {
            throw new BadRequestException("La puntuacion debe estar entre 1 y 5");
        };

        Rating rate = ratingMapper.toEntity(ratingCreateUpdateDTO);

        rate.setEvent(event);
        rate.setStudent(student);
        rate.setCreated_at(LocalDateTime.now());
        rate.setRate(ratingCreateUpdateDTO.getRate());

        return ratingMapper.toDetailsDTO(ratingRepository.save(rate));
    }

    @Transactional
    @Override
    public RatingDetailsDTO update(Integer id, RatingCreateUpdateDTO updateRate) {
        Rating ratingFromDb = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La puntuacion con ID " + id + " no fue encontrado"));

        ratingRepository.findByRate(updateRate.getRate())
                .filter(existingPuntuacion -> !existingPuntuacion.getId().equals(id))
                .ifPresent(existingPuntuacion -> {
                    throw new BadRequestException("La puntuacion ya existe");
                });

        ratingRepository.findByEventIdAndStudentId(updateRate.getEventId(), updateRate.getStudentId())
                .filter(existingPuntuacion -> !existingPuntuacion.getId().equals(id))
                .ifPresent(existingPuntuacion -> {
                    throw new RuntimeException("La puntuacion ya existe para este evento y estudiante");
                });

        Event event = eventRepository.findById(updateRate.getEventId())
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id: " + updateRate.getEventId()));

        Student student = studentRepository.findById(updateRate.getStudentId())
                .orElseThrow(()->new ResourceNotFoundException("Student not found with id: " + updateRate.getStudentId()));

        //Actualizar los campos
        ratingFromDb.setEvent(event);
        ratingFromDb.setStudent(student);
        ratingFromDb.setUpdated_at(LocalDateTime.now());
        ratingFromDb.setRate(updateRate.getRate());

        return ratingMapper.toDetailsDTO(ratingRepository.save(ratingFromDb));
    }

    @Override
    public void delete(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificacion no existente con id: " + id));
        ratingRepository.delete(rating);
    }
}