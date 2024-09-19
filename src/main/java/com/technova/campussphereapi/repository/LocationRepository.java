package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
