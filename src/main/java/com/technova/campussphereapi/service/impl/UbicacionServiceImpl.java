package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Ubicacion;
import com.technova.campussphereapi.repository.UbicacionRepository;
import com.technova.campussphereapi.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UbicacionServiceImpl  implements UbicacionService {

   private final UbicacionRepository ubicacionRepository;

   @Transactional(readOnly = true)
    @Override
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ubicacion> paginate(Pageable pageable) {
        return ubicacionRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Ubicacion getUbicacionById(int id) {
        return ubicacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Ubicacion createUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @Transactional
    @Override
    public Ubicacion updateUbicacion(Integer id, Ubicacion ubicacion) {
    Ubicacion ubicaciondb = getUbicacionById(id);ubicacion.setId(id);
    ubicaciondb.setCiudad(ubicacion.getCiudad());
    ubicaciondb.setPais(ubicacion.getPais());
    ubicaciondb.setDireccion(ubicacion.getDireccion());
    return ubicacionRepository.save(ubicaciondb);

    }
    @Transactional
    @Override
    public void deleteUbicacion(int id) {
    Ubicacion ubicacion = getUbicacionById(id);
    ubicacionRepository.delete(ubicacion);
    }
}
