package com.arrienda.proj.controllers;

import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@CrossOrigin
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PagoDTO> getAllPagos() {
        return pagoService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO getPagoById(@PathVariable Long id) {
        return pagoService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO createPago(@RequestBody PagoDTO pagoDTO) {
        return pagoService.save(pagoDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PagoDTO updatePago(@PathVariable Long id, @RequestBody PagoDTO updatedPagoDTO) {
        return pagoService.update(id, updatedPagoDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePago(@PathVariable Long id) {
        pagoService.delete(id);
    }
}
