package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.ComentarioDTO;
import com.technova.campussphereapi.dto.ComentarioReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComentarioService {
    List<ComentarioDTO> getAll();

    List<ComentarioReportDTO> getComentarioReportByDate();

    Page<ComentarioDTO> paginate(Pageable pageable);

    ComentarioDTO findById(Integer id);

    ComentarioDTO create(ComentarioDTO comentarioDTO);

    ComentarioDTO update(Integer id, ComentarioDTO updateComentarioDTO);

    void delete(Integer id);
}