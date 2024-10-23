package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;
import com.technova.campussphereapi.dto.InscriptionReportDTO;
import jakarta.mail.MessagingException;
import java.util.List;

public interface InscriptionService {
    InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionDTO) throws MessagingException;
    //List<InscriptionReportDTO> getInscriptionPerEventReport();
    void delete(Integer eventId);
    List<InscriptionDetailsDTO> getAllInscription();
    List<InscriptionDetailsDTO> getInscriptionHistoryByUserId();
    InscriptionDetailsDTO confirmInscription(Integer inscriptionId);
    InscriptionDetailsDTO getInscriptionById(Integer id);

    //List<InscriptionReportDTO> getInscriptionEventReportDate();
}
