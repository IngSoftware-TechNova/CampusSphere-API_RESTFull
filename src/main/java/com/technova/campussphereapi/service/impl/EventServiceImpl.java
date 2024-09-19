package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.model.entity.Category;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Price;
import com.technova.campussphereapi.model.entity.Location;
import com.technova.campussphereapi.repository.CategoryRepository;
import com.technova.campussphereapi.repository.EventRepository;
import com.technova.campussphereapi.repository.PriceRepository;
import com.technova.campussphereapi.repository.LocationRepository;
import com.technova.campussphereapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final PriceRepository priceRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Event findById(Integer id) {
        return eventRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Evento not found"));
    }

    @Transactional
    @Override
    public Event create(Event event) {
        // Asigna los atributos necesarios antes de guardar
        Category category = categoryRepository.findById(event.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria not found with id: " + event.getCategory().getId()));

        Location location = locationRepository.findById(event.getLocation().getId())
                        .orElseThrow(() -> new RuntimeException("Ubicacion not found with id: " + event.getLocation().getId()));

        Price price = priceRepository.findById(event.getPrice().getId())
                        .orElseThrow(() -> new RuntimeException("Tarifario not found with id: " + event.getPrice().getId()));

        event.setCategory(category);
        event.setLocation(location);
        event.setPrice(price);

        return eventRepository.save(event);
    }

    @Transactional
    @Override
    public Event update(Integer id, Event updateEvent) {

        Event eventFromDB = findById(id);

        // Asigna los atributos necesarios antes de guardar
        Category category = categoryRepository.findById(updateEvent.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria not found with id: " + updateEvent.getCategory().getId()));

        Location location = locationRepository.findById(updateEvent.getLocation().getId())
                .orElseThrow(() -> new RuntimeException("Ubicacion not found with id: " + updateEvent.getLocation().getId()));

        Price price = priceRepository.findById(updateEvent.getPrice().getId())
                .orElseThrow(() -> new RuntimeException("Tarifario not found with id: " + updateEvent.getPrice().getId()));

        eventFromDB.setName(updateEvent.getName());
        eventFromDB.setDescription(updateEvent.getDescription());
        eventFromDB.setCapacity(updateEvent.getCapacity());
        eventFromDB.setLocation(location);
        eventFromDB.setCategory(category);
        eventFromDB.setPrice(price);

        return eventRepository.save(eventFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Event event = findById(id);
        eventRepository.delete(event);
    }
}
