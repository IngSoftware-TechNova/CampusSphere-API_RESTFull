package com.technova.campussphereapi.service;

import com.technova.campussphereapi.dto.PriceDTO;
import com.technova.campussphereapi.model.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PriceService {
    List<PriceDTO> findAll();
    Page<PriceDTO> paginate(Pageable pageable);
    PriceDTO findById(Integer id);
    PriceDTO create(PriceDTO priceDTO);
    PriceDTO update(Integer id, PriceDTO updatePriceDTO);
    void delete(Integer id);
}