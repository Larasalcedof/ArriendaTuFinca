package com.arrienda.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arrienda.proj.entity.Pago;
import com.arrienda.proj.repositories.PagoRepository;

@RestController
@RequestMapping("/pagos")
@CrossOrigin
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pago getPagoById(@PathVariable Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pago createPago(@RequestBody Pago pago) {
        return pagoRepository.save(pago);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pago updatePago(@PathVariable Long id, @RequestBody Pago updatedPago) {
        if (pagoRepository.existsById(id)) {
            updatedPago.setId(id);
            return pagoRepository.save(updatedPago);
        }
        return null; // or handle the case when the pago with the given id doesn't exist
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
    }
}
