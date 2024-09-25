package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.PriceDTO;

public interface PriceService {
    PriceDTO findById(Integer id);
}