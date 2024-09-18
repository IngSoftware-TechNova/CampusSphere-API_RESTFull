package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TarifarioService {
    List<Price> findAll();
    Page<Price> paginate(Pageable pageable);
    Price findById(Integer id);
    Price create(Price price);
    Price update(Integer id, Price updatePrice);
    void delete(Integer id);
}
