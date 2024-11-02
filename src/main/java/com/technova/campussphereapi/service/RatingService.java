package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.RatingCreateUpdateDTO;
import com.technova.campussphereapi.dto.RatingDetailsDTO;
import com.technova.campussphereapi.dto.PuntuacionReportDTO;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RatingService {
    List<PuntuacionReportDTO> getRateReportByDate();

    List<RatingDetailsDTO> findAll();

    RatingDetailsDTO findById(Integer id);

    RatingDetailsDTO create(RatingCreateUpdateDTO ratingCreateDTO) throws MessagingException;

    RatingDetailsDTO update(Integer id, RatingCreateUpdateDTO ratingUpdateDTO);

    void delete(Integer id);
}