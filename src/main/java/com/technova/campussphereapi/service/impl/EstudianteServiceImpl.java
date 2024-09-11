package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Estudiante;
import com.technova.campussphereapi.repository.EstudianteRepository;
import com.technova.campussphereapi.service.EstudianteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Transactional
    @Override
    public Estudiante create(Estudiante estudiante) {
        if (estudianteRepository.existsEstudianteByEmail(estudiante.getEmail())) {
           throw new RuntimeException("El email ya está registrado");
        } else {
            estudiante.setCreatedAt(LocalDateTime.now());
            return estudianteRepository.save(estudiante);
        }

    }



    @Transactional
    @Override
    public Estudiante findById(Integer id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante not found"));
    }

    @Transactional
    @Override
    public Estudiante update(Integer id, Estudiante updateEstudiante) {

        Estudiante estudianteFromDB = findById(id);

        estudianteFromDB.setNombre(updateEstudiante.getNombre());
        estudianteFromDB.setEmail(updateEstudiante.getEmail());
        estudianteFromDB.setContraseña(updateEstudiante.getContraseña());
        estudianteFromDB.setUpdatedAt(LocalDateTime.now());
        estudianteFromDB.setCarrera(updateEstudiante.getCarrera());
        estudianteFromDB.setCreatedAt(LocalDateTime.now());

        return estudianteRepository.save(estudianteFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Estudiante estudiante = findById(id);
        estudianteRepository.delete(estudiante);
    }


    /*@Transactional
    @Override
    public Estudiante registerEstudiante(Estudiante estudiante) {
        if (estudianteRepository.existsEstudianteByEmail(estudiante.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Establecer fechas de creación
        estudiante.setCreatedAt(LocalDateTime.now());

        return estudianteRepository.save(estudiante);
    }
     */
}
