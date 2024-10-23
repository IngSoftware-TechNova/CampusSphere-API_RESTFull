package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;

import java.util.List;

public interface InscriptionService {
    InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO);
    //List<InscriptionReportDTO> getInscriptionEventReportDate();
    List<InscriptionDetailsDTO> getInscriptionHistoryByUserId();
    List<InscriptionDetailsDTO> getAllInscription();
    InscriptionDetailsDTO confirmInscription(Integer inscriptionId);
    InscriptionDetailsDTO getInscriptionById(Integer id);
}
