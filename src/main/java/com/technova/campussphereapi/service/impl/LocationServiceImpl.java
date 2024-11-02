package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.LocationDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.LocationMapper;
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
   private final LocationMapper locationMapper;

   @Transactional(readOnly = true)
    @Override
    public List<LocationDTO> findAll() {
    List<Location> locations = locationRepository.findAll();
    return locations.stream().map(locationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<LocationDTO> paginate(Pageable pageable) {
    Page<Location> locations = locationRepository.findAll(pageable);
    return locations.map(locationMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public LocationDTO findById(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Location with ID: " + id + " was not found!"));

        return locationMapper.toDTO(location);
    }

    @Transactional
    @Override
    public LocationDTO create(LocationDTO locationDTO) {
    locationRepository.findByLocation(locationDTO.getLocation())
            .ifPresent(existingLocation -> {
             throw new BadRequestException("The location already exists!");
            });
    Location location = locationMapper.toEntity(locationDTO);
    location = locationRepository.save(location);
    return locationMapper.toDTO(location);
    }

    @Transactional
    @Override
    public LocationDTO update(Integer id, LocationDTO updatelocationDTO) {
    Location locationFromDb = locationRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("The Location with ID: " + id + " was not found!"));

            locationRepository.findByLocation(updatelocationDTO.getLocation())
            .filter(existingLocation -> !existingLocation.getId().equals(id))
            .ifPresent(existingLocation -> {
             throw new BadRequestException("The location already exists!");
            });

            locationFromDb.setLocation(updatelocationDTO.getLocation());
            locationFromDb.setCity(updatelocationDTO.getCity());
            locationFromDb.setCountry(updatelocationDTO.getCountry());
            locationFromDb = locationRepository.save(locationFromDb);
            return locationMapper.toDTO(locationFromDb);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
    Location location = locationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The Location with ID: " + id + " was not found!"));
    locationRepository.delete(location);
    }
}