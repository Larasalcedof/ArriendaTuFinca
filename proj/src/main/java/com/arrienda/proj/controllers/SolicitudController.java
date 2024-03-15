package com.arrienda.proj.controllers;

import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO> getAllSolicitudes() {
        return solicitudService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO getSolicitudById(@PathVariable Long id) {
        return solicitudService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO createSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.save(solicitudDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO updateSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO updatedSolicitudDTO) {
        return solicitudService.update(id, updatedSolicitudDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudService.delete(id);
    }
}
