package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    Optional<Price> findByPriceAndDescription(BigDecimal price, String description);

}
