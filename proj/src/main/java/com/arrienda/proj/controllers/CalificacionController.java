package com.arrienda.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arrienda.proj.entity.Calificacion;
import com.arrienda.proj.repositories.CalificacionRepository;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin
public class CalificacionController {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Calificacion getCalificacionById(@PathVariable Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Calificacion createCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Calificacion updateCalificacion(@PathVariable Long id, @RequestBody Calificacion updatedCalificacion) {
        if (calificacionRepository.existsById(id)) {
            updatedCalificacion.setId(id);
            return calificacionRepository.save(updatedCalificacion);
        }
        return null; // or handle the case when the calificacion with the given id doesn't exist
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCalificacion(@PathVariable Long id) {
        calificacionRepository.deleteById(id);
    }
}
