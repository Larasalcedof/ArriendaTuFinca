package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.entity.Usuario;

class UsuarioTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Doe");
        usuario.setRol(1);
        usuario.setStatus(1);

        // Assert
        assertEquals(1L, usuario.getId());
        assertEquals("John Doe", usuario.getNombre());
        assertEquals(1, usuario.getRol());
        assertEquals(1, usuario.getStatus());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Usuario usuario = new Usuario();

        // Assert
        assertNotNull(usuario);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Credenciales credenciales = new Credenciales();
        Usuario usuario = new Usuario(1L, "John Doe", 1, 1, credenciales);

        // Assert
        assertEquals(1L, usuario.getId());
        assertEquals("John Doe", usuario.getNombre());
        assertEquals(1, usuario.getRol());
        assertEquals(1, usuario.getStatus());
        assertEquals(credenciales, usuario.getCredenciales());
    }
}
