package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.LocationDTO;

public interface LocationService {
    LocationDTO findById(Integer id);
}