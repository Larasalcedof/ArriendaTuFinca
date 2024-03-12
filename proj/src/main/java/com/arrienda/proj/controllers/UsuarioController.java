package com.arrienda.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arrienda.proj.entity.Usuario;
import com.arrienda.proj.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
        if (usuarioRepository.existsById(id)) {
            updatedUsuario.setId(id);
            return usuarioRepository.save(updatedUsuario);
        }
        return null; // or handle the case when the user with the given id doesn't exist
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
