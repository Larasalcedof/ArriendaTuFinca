package com.arrienda.proj.controllers;

import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.services.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propiedades")
@CrossOrigin
public class PropiedadController {

    private final PropiedadService propiedadService;

    @Autowired
    public PropiedadController(PropiedadService propiedadService) {
        this.propiedadService = propiedadService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> getAllPropiedades() {
        return propiedadService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO getPropiedadById(@PathVariable Long id) {
        return propiedadService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO createPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
        return propiedadService.save(propiedadDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO updatePropiedad(@PathVariable Long id, @RequestBody PropiedadDTO updatedPropiedadDTO) {
        return propiedadService.update(id, updatedPropiedadDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePropiedad(@PathVariable Long id) {
        propiedadService.delete(id);
    }
}
