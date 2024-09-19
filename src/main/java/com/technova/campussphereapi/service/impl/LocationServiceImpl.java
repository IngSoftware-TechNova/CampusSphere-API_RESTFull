package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Location;
import com.technova.campussphereapi.repository.LocationRepository;
import com.technova.campussphereapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicacion not found"));
    }
}
