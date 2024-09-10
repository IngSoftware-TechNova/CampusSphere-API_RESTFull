package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Categoria;
import com.technova.campussphereapi.model.entity.Evento;
import com.technova.campussphereapi.model.entity.Tarifario;
import com.technova.campussphereapi.model.entity.Ubicacion;
import com.technova.campussphereapi.repository.CategoriaRepository;
import com.technova.campussphereapi.repository.EventoRepository;
import com.technova.campussphereapi.repository.TarifarioRepository;
import com.technova.campussphereapi.repository.UbicacionRepository;
import com.technova.campussphereapi.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TarifarioRepository tarifarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Evento findById(Integer id) {
        return eventoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Evento not found"));
    }

    @Transactional
    @Override
    public Evento create(Evento evento) {
        // Asigna los atributos necesarios antes de guardar
        Categoria categoria = categoriaRepository.findById(evento.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria not found with id: " + evento.getCategoria().getId()));

        Ubicacion ubicacion = ubicacionRepository.findById(evento.getUbicacion().getId())
                        .orElseThrow(() -> new RuntimeException("Ubicacion not found with id: " + evento.getUbicacion().getId()));

        Tarifario tarifario = tarifarioRepository.findById(evento.getTarifario().getId())
                        .orElseThrow(() -> new RuntimeException("Tarifario not found with id: " + evento.getTarifario().getId()));

        evento.setCategoria(categoria);
        evento.setUbicacion(ubicacion);
        evento.setTarifario(tarifario);

        return eventoRepository.save(evento);
    }

    @Transactional
    @Override
    public Evento update(Integer id, Evento updateEvento) {

        Evento eventoFromDB = findById(id);
        eventoFromDB.setNombre(updateEvento.getNombre());
        eventoFromDB.setDescripcion(updateEvento.getDescripcion());
        eventoFromDB.setCapacidad(updateEvento.getCapacidad());
        eventoFromDB.setUbicacion(updateEvento.getUbicacion());
        eventoFromDB.setCategoria(updateEvento.getCategoria());
        eventoFromDB.setTarifario(updateEvento.getTarifario());

        return eventoRepository.save(eventoFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Evento evento = findById(id);
        eventoRepository.delete(evento);
    }
}
