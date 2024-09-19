package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
