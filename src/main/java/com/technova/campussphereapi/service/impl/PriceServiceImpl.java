package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.repository.PriceRepository;
import com.technova.campussphereapi.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Transactional(readOnly = true)
    @Override
    public Price findById(Integer id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarifario not found"));
    }
}
