package com.technova.campussphereapi.api;

import com.technova.campussphereapi.model.entity.Location;
import com.technova.campussphereapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;
    @GetMapping
    public ResponseEntity<List<Location>> list() {
        List<Location> location = locationService.findAll();
        return new ResponseEntity<List<Location>>(location, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Location>> paginate(
            @PageableDefault(size = 5, sort ="name") Pageable pageable) {
        Page<Location> ubicacion = locationService.paginate(pageable);
        return new ResponseEntity<>(ubicacion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> search(@PathVariable("id") Integer id) {
        Location location = locationService.findById(id);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        Location newLocation = locationService.create(location);
        return new ResponseEntity<Location>(newLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable("id") Integer id, @RequestBody Location location) {
        Location updatedCategoria = locationService.update(id, location);
        return new ResponseEntity<Location>( updatedCategoria,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> delete(@PathVariable("id") Integer id) {
        locationService.delete(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }
}
