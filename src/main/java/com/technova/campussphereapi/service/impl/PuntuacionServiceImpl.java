package com.technova.campussphereapi.service.impl;


import com.technova.campussphereapi.dto.PuntuacionDTO;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.PuntuacionMapper;
import com.technova.campussphereapi.model.entity.Puntuacion;
import com.technova.campussphereapi.repository.PuntuacionRepository;
import com.technova.campussphereapi.service.PuntuacionService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class PuntuacionServiceImpl implements PuntuacionService {
    private final PuntuacionRepository PuntuacionRepository;
    private final PuntuacionMapper PuntuacionMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PuntuacionDTO> getAll() {
        List<Puntuacion> puntuaciones = PuntuacionRepository.findAll();
        return puntuaciones.stream()
                .map(PuntuacionMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PuntuacionReportDTO> getPuntuacionReportByDate() {
        List<Object[]> results = PuntuacionRepository.getPuntuacionReportByDate();
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

    @Transactional(readOnly = true)
    @Override
    public Page<PuntuacionDTO> paginate(Pageable pageable) {
        Page<Puntuacion> puntuaciones = PuntuacionRepository.findAll(pageable);
        return puntuaciones.map(PuntuacionMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public PuntuacionDTO findById(Integer id) {
        Puntuacion puntuacion = PuntuacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La puntuacion con ID " + id + " no fue encontrado"));
        return PuntuacionMapper.toDTO(puntuacion);
    }

    @Transactional
    @Override
    public PuntuacionDTO create(PuntuacionDTO PuntuacionDTO) {
        if (PuntuacionDTO.getPuntuacion() < 1 || PuntuacionDTO.getPuntuacion() > 5) {
            throw new BadRequestException("La puntuacion debe estar entre 1 y 5");
        };
        PuntuacionRepository.findByEventoAndEstudiante(PuntuacionDTO.getEvento(), PuntuacionDTO.getEstudiante())
                .ifPresent(existingPuntuacion -> {
                    throw new BadRequestException("La puntuaciones ya existe para este evento y estudiante");
                });
        Puntuacion puntuacion = PuntuacionMapper.toEntity(PuntuacionDTO);
        puntuacion = PuntuacionRepository.save(puntuacion);
        return PuntuacionMapper.toDTO(puntuacion);
    }

    @Transactional
    @Override
    public PuntuacionDTO update(Integer id, PuntuacionDTO updatePuntuacionDTO) {
        Puntuacion puntuacionFromDb = PuntuacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El puntuacion con ID " + id + " no fue encontrado"));

        PuntuacionRepository.findByPuntuacion(updatePuntuacionDTO.getPuntuacion())
                .filter(existingPuntuacion -> !existingPuntuacion.getId().equals(id))
                .ifPresent(existingPuntuacion -> {
                    throw new BadRequestException("La puntuacion ya existe");
                });

        PuntuacionRepository.findByEventoAndEstudiante(updatePuntuacionDTO.getEvento(), updatePuntuacionDTO.getEstudiante())
                .filter(existingPuntuacion -> !existingPuntuacion.getId().equals(id))
                .ifPresent(existingPuntuacion -> {
                    throw new RuntimeException("La puntuacion ya existe para este evento y estudiante");
                });

        //Actualizar los campos
        puntuacionFromDb.setPuntuacion(updatePuntuacionDTO.getPuntuacion());
        puntuacionFromDb.setEvento(updatePuntuacionDTO.getEvento());
        puntuacionFromDb.setEstudiante(updatePuntuacionDTO.getEstudiante());
        puntuacionFromDb = PuntuacionRepository.save(puntuacionFromDb);
        return PuntuacionMapper.toDTO(puntuacionFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Puntuacion puntuacion = PuntuacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El puntuacion con ID " + id + " no fue encontrado"));
        PuntuacionRepository.delete(puntuacion);
    }
}