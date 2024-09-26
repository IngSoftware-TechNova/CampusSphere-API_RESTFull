package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByNameAndDescription(String name, String description);

    //List<Event> findTop7ByOrderByPriceAtDesc();

    @Query(value = "SELECT * FROM fn_filer_events(?, ?, ?, ?)", nativeQuery = true)
    List<Object[]> getEventsFiltered(BigDecimal precioMin, BigDecimal precioMax, String categoriaName, String ubicacion);

}
