package com.arrienda.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arrienda.proj.entity.Solicitud;
import com.arrienda.proj.repositories.SolicitudRepository;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud getSolicitudById(@PathVariable Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Solicitud updateSolicitud(@PathVariable Long id, @RequestBody Solicitud updatedSolicitud) {
        if (solicitudRepository.existsById(id)) {
            updatedSolicitud.setId(id);
            return solicitudRepository.save(updatedSolicitud);
        }
        return null; // or handle the case when the solicitud with the given id doesn't exist
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudRepository.deleteById(id);
    }
}
