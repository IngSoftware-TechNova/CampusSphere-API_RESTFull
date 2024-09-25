package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.InscriptionCreateUpdateDTO;
import com.technova.campussphereapi.dto.InscriptionDetailsDTO;

public interface InscriptionService {
    InscriptionDetailsDTO create(InscriptionCreateUpdateDTO inscriptionCreateUpdateDTO);
}
