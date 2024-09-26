package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.PuntuacionDTO;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PuntuacionService {
    List<PuntuacionDTO> getAll();

    List<PuntuacionReportDTO> getPuntuacionReportByDate();

    Page<PuntuacionDTO> paginate(Pageable pageable);

    PuntuacionDTO findById(Integer id);

    PuntuacionDTO create(PuntuacionDTO PuntuacionDTO);

    PuntuacionDTO update(Integer id, PuntuacionDTO updatePuntuacionDTO);

    void delete(Integer id);
}