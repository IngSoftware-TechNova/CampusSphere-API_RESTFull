package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Optional<Location> findByLocationAndCity(String location, String city);

}