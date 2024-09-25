package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.PriceDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.PriceMapper;
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
    private final PriceMapper priceMapper;

    @Transactional(readOnly = true)
    @Override
    public PriceDTO findById(Integer id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El precio con ID " + id + " no fue encontrado."));
        return priceMapper.toDTO(price);
    }
}