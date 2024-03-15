package com.arrienda.proj.controllers;

import com.arrienda.proj.dto.CalificacionDTO;
import com.arrienda.proj.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin
public class CalificacionController {

    private final CalificacionService calificacionService;

    @Autowired
    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalificacionDTO> getAllCalificaciones() {
        return calificacionService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO getCalificacionById(@PathVariable Long id) {
        return calificacionService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO createCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        return calificacionService.save(calificacionDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalificacionDTO updateCalificacion(@PathVariable Long id, @RequestBody CalificacionDTO updatedCalificacionDTO) {
        return calificacionService.update(id, updatedCalificacionDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCalificacion(@PathVariable Long id) {
        calificacionService.delete(id);
    }
}
