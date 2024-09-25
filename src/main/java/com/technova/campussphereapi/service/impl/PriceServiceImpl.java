package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.PriceDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.PriceMapper;
import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.repository.PriceRepository;
import com.technova.campussphereapi.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PriceDTO> findAll() {
        List<Price> prices = priceRepository.findAll();
        return prices.stream()
                .map(priceMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PriceDTO> paginate(Pageable pageable) {
        Page<Price> prices = priceRepository.findAll(pageable);
        return prices.map(priceMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public PriceDTO findById(Integer id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La tarifa con ID " + id + " no fue encontrada"));
        return priceMapper.toDTO(price);
    }

    @Transactional
    @Override
    public PriceDTO create(PriceDTO priceDTO) {
        priceRepository.findByPriceAndDescription(priceDTO.getPrice(), priceDTO.getDescription())
                .ifPresent(existingPrice -> {
                    throw new BadRequestException("La tarifa ya existe");
                });

        Price price = priceMapper.toEntity(priceDTO);
        price = priceRepository.save(price);
        return priceMapper.toDTO(price);
    }

    @Transactional
    @Override
    public PriceDTO update(Integer id, PriceDTO updatePriceDTO) {
        Price priceFromDb = priceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La tarifa con ID " + id + " no fue encontrado"));

        priceRepository.findByPriceAndDescription(updatePriceDTO.getPrice(), updatePriceDTO.getDescription())
                .filter(existingPrice -> !existingPrice.getId().equals(id))
                .ifPresent(existingPrice -> {
                    throw new BadRequestException("La tarifa ya existe");
                });

        // Actualizar los campos
        priceFromDb.setPrice(updatePriceDTO.getPrice());
        priceFromDb.setDescription(updatePriceDTO.getDescription());

        priceFromDb = priceRepository.save(priceFromDb);
        return priceMapper.toDTO(priceFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La tarifa con ID " + id + " no fue encontrado"));
        priceRepository.delete(price);
    }
}
