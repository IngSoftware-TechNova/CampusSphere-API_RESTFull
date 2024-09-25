package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.LocationDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.LocationMapper;
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
    private final LocationMapper locationMapper;

    @Transactional(readOnly = true)
    @Override
    public LocationDTO findById(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La locacion con ID " + id + " no fue encontrada."));
        return locationMapper.toDTO(location);
    }
}