package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Ubicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UbicacionService {
    List<Ubicacion> getAllUbicaciones();
    Page<Ubicacion> paginate(Pageable pageable);
    Ubicacion getUbicacionById(int id);
    Ubicacion createUbicacion(Ubicacion ubicacion);
    Ubicacion updateUbicacion(Integer id,Ubicacion ubicacion);
    void deleteUbicacion(int id);
}
