package com.arrienda.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.repositories.PropiedadRepository;

@RestController
@RequestMapping("/propiedades")
@CrossOrigin
public class PropiedadController {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Propiedad> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Propiedad getPropiedadById(@PathVariable Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Propiedad createPropiedad(@RequestBody Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Propiedad updatePropiedad(@PathVariable Long id, @RequestBody Propiedad updatedPropiedad) {
        if (propiedadRepository.existsById(id)) {
            updatedPropiedad.setId(id);
            return propiedadRepository.save(updatedPropiedad);
        }
        return null; // or handle the case when the propiedad with the given id doesn't exist
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePropiedad(@PathVariable Long id) {
        propiedadRepository.deleteById(id);
    }
}
