package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Location;
import com.technova.campussphereapi.repository.LocationRepository;
import com.technova.campussphereapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

   private final LocationRepository locationRepository;

   @Transactional(readOnly = true)
    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Location> paginate(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Location findById(int id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Location create(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    @Override
    public Location update(Integer id, Location location) {
    Location ubicaciondb = findById(id);
        location.setId(id);
    ubicaciondb.setCiudad(location.getCiudad());
    ubicaciondb.setPais(location.getPais());
    ubicaciondb.setDireccion(location.getDireccion());
    return locationRepository.save(ubicaciondb);

    }
    @Transactional
    @Override
    public void delete(int id) {
    Location location = findById(id);
    locationRepository.delete(location);
    }
}
