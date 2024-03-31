package com.arrienda.proj.controllers;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.services.CredencialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credenciales")
public class CredencialesController {

    private final CredencialesService credencialesService;

    @Autowired
    public CredencialesController(CredencialesService credencialesService) {
        this.credencialesService = credencialesService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredencialesDTO> createCredenciales(@RequestBody CredencialesDTO credencialesDTO) {
        CredencialesDTO createdCredenciales = credencialesService.save(credencialesDTO);
        return new ResponseEntity<>(createdCredenciales, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CredencialesDTO>> getAllCredenciales() {
        List<CredencialesDTO> credencialesList = credencialesService.findAll();
        return new ResponseEntity<>(credencialesList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredencialesDTO> getCredencialesById(@PathVariable Long id) {
        CredencialesDTO credencialesDTO = credencialesService.findById(id);
        if (credencialesDTO != null) {
            return new ResponseEntity<>(credencialesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CredencialesDTO> updateCredenciales(@PathVariable Long id, @RequestBody CredencialesDTO updatedCredencialesDTO) {
        CredencialesDTO updatedCredenciales = credencialesService.update(id, updatedCredencialesDTO);
        if (updatedCredenciales != null) {
            return new ResponseEntity<>(updatedCredenciales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCredenciales(@PathVariable Long id) {
        boolean deleted = credencialesService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
