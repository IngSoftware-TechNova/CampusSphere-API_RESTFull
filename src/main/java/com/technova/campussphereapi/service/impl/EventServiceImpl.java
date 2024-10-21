package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.EventCreateUpdateDTO;
import com.technova.campussphereapi.dto.EventDetailsDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventMapper;
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
    private final EventMapper eventMapper;

    @Transactional(readOnly = true)
    @Override
    public List<EventDetailsDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toDetailsDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EventDetailsDTO findById(Integer id) {

        Event event = eventRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con id: " + id));

        return eventMapper.toDetailsDTO(event);
    }

    @Transactional
    @Override
    public EventDetailsDTO create(EventCreateUpdateDTO eventCreateUpdateDTO) {

        eventRepository.findByNameAndDescription(eventCreateUpdateDTO.getName(), eventCreateUpdateDTO.getDescription())
                .ifPresent(event -> {
                    throw new BadRequestException("El evento ya existe");
                });

        // Asigna los atributos necesarios antes de guardar
        Category category = categoryRepository.findById(eventCreateUpdateDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found with id: " + eventCreateUpdateDTO.getCategoryId()));

        Location location = locationRepository.findById(eventCreateUpdateDTO.getLocationId())
                        .orElseThrow(() -> new ResourceNotFoundException("Ubicacion not found with id: " + eventCreateUpdateDTO.getLocationId()));

        Price price = priceRepository.findById(eventCreateUpdateDTO.getPriceId())
                        .orElseThrow(() -> new ResourceNotFoundException("Tarifario not found with id: " + eventCreateUpdateDTO.getPriceId()));

        Event event = eventMapper.toEntity(eventCreateUpdateDTO);

        event.setCategory(category);
        event.setLocation(location);
        event.setPrice(price);

        return eventMapper.toDetailsDTO(eventRepository.save(event));
    }

    @Transactional
    @Override
    public EventDetailsDTO update(Integer id, EventCreateUpdateDTO updateEvent) {

        Event eventFromDB = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento non existente con id" + id));

        eventRepository.findByNameAndDescription(updateEvent.getName(), updateEvent.getDescription())
                .ifPresent(event -> {
                    throw new BadRequestException("El evento ya existe");
                });

        // Asigna los atributos necesarios antes de guardar
        Category category = categoryRepository.findById(updateEvent.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria not found with id: " + updateEvent.getCategoryId()));

        Location location = locationRepository.findById(updateEvent.getLocationId())
                .orElseThrow(() -> new RuntimeException("Ubicacion not found with id: " + updateEvent.getLocationId()));

        Price price = priceRepository.findById(updateEvent.getPriceId())
                .orElseThrow(() -> new RuntimeException("Tarifario not found with id: " + updateEvent.getPriceId()));

        //event = eventMapper.toEntity(updateEvent);

        eventFromDB.setName(updateEvent.getName());
        eventFromDB.setDescription(updateEvent.getDescription());
        eventFromDB.setCapacity(updateEvent.getCapacity());
        eventFromDB.setLocation(location);
        eventFromDB.setCategory(category);
        eventFromDB.setPrice(price);

        return  eventMapper.toDetailsDTO(eventRepository.save(eventFromDB));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non existente con id: " + id));
        eventRepository.delete(event);
    }

}
