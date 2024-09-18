package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.repository.PriceRepository;
import com.technova.campussphereapi.service.TarifarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PriceServiceImpl implements TarifarioService {
    private final PriceRepository priceRepository;



    @Transactional(readOnly = true)
    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Price> paginate(Pageable pageable) {
        return priceRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Price findById(Integer id) {
        return priceRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Tarifa no encontrada"));
    }

    @Transactional
    @Override
    public Price create(Price price) {
        return priceRepository.save(price);
    }

    @Transactional
    @Override
    public Price update(Integer id, Price updatePrice) {
        Price tarifaDeDB = findById(id);
        tarifaDeDB.setPrecio(updatePrice.getPrecio());
        tarifaDeDB.setDescripcion(updatePrice.getDescripcion());
        return priceRepository.save(tarifaDeDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Price tarifa = findById(id);
        priceRepository.delete(tarifa);
    }
}
