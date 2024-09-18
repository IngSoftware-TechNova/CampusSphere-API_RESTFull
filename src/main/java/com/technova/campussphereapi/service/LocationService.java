package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Page<Location> paginate(Pageable pageable);
    Location findById(int id);
    Location create(Location location);
    Location update(Integer id, Location location);
    void delete(int id);
}
