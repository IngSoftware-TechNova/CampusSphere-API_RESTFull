package com.technova.campussphereapi.service.impl;


import com.technova.campussphereapi.dto.ComentarioDTO;
import com.technova.campussphereapi.dto.ComentarioReportDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.ComentarioMapper;
import com.technova.campussphereapi.model.entity.Comentario;
import com.technova.campussphereapi.repository.ComentarioRepository;
import com.technova.campussphereapi.service.ComentarioService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ComentarioDTO> getAll() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ComentarioReportDTO> getComentarioReportByDate() {
        List<Object[]> results = comentarioRepository.getComentarioReportByDate();
        //Mapeo de la lista de objetos a una lista de ComentarioDTO
        List<ComentarioReportDTO> comentarioReportDTOS = results.stream()
                .map(result ->
                    new ComentarioReportDTO (
                            ((Integer) result[0]).intValue(),
                            (String) result[1]
                    )
                ).toList();
        return comentarioReportDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ComentarioDTO> paginate(Pageable pageable) {
        Page<Comentario> comentarios = comentarioRepository.findAll(pageable);
        return comentarios.map(comentarioMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ComentarioDTO findById(Integer id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El comentario con ID " + id + " no fue encontrado"));
        return comentarioMapper.toDTO(comentario);
    }

    @Transactional
    @Override
    public ComentarioDTO create(ComentarioDTO comentarioDTO) {
        comentarioRepository.findByComentario(comentarioDTO.getComentario())
                .ifPresent(existingComentario -> {
                    throw new BadRequestException("El comentario ya existe");
                });
        comentarioRepository.findByEventoAndEstudiante(comentarioDTO.getEvento(), comentarioDTO.getEstudiante())
                .ifPresent(existingComentario -> {
                    throw new BadRequestException("El comentario ya existe para este evento y estudiante");
                });
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        comentario.setFechaComentar(LocalDateTime.now());
        comentario = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(comentario);
    }

    @Transactional
    @Override
    public ComentarioDTO update(Integer id, ComentarioDTO updateComentarioDTO) {
        Comentario comentarioFromDb = comentarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("El comentario con ID " + id + " no fue encontrado"));

        comentarioRepository.findByComentario(updateComentarioDTO.getComentario())
                .filter(existingComentario -> !existingComentario.getId().equals(id))
                .ifPresent(existingComentario -> {
                        throw new BadRequestException("El comentario ya existe");
                });

        comentarioRepository.findByEventoAndEstudiante(updateComentarioDTO.getEvento(), updateComentarioDTO.getEstudiante())
                .filter(existingComentario -> !existingComentario.getId().equals(id))
                .ifPresent(existingComentario -> {
                        throw new RuntimeException("El comentario ya existe para este evento y estudiante");
                });

        //Actualizar los campos
        comentarioFromDb.setComentario(updateComentarioDTO.getComentario());
        comentarioFromDb.setEvento(updateComentarioDTO.getEvento());
        comentarioFromDb.setEstudiante(updateComentarioDTO.getEstudiante());
        comentarioFromDb.setFechaComentar(LocalDateTime.now());
        comentarioFromDb = comentarioRepository.save(comentarioFromDb);
        return comentarioMapper.toDTO(comentarioFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El comentario con ID " + id + " no fue encontrado"));
        comentarioRepository.delete(comentario);
    }
}

