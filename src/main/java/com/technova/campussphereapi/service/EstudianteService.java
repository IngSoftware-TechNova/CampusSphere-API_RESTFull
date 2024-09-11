package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Estudiante;

public interface EstudianteService {
    Estudiante create(Estudiante estudiante);
    Estudiante findById(Integer id);
    Estudiante update(Integer id, Estudiante updateEstudiante);
    void delete(Integer id);
}
