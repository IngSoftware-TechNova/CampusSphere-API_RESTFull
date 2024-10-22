package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;
import jakarta.mail.MessagingException;

import java.util.List;

public interface InscriptionService {
    InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO) throws MessagingException;
    List<InscriptionReportDTO> getInscriptionPerEventReport();
}
