package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.Evento;

import java.util.List;

public interface EventoService {
    List<Evento> getAll();
    Evento findById(Integer id);
    Evento create(Evento evento);
    Evento update(Integer id, Evento updateEvento);
    void delete(Integer id);
}

