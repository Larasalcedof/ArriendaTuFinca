package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arrienda.proj.entity.Credenciales;

class CredencialesTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Credenciales credenciales = new Credenciales();
        credenciales.setId(1L);
        credenciales.setCorreoElectronico("test@example.com");
        credenciales.setContrasena("password123");

        // Assert
        assertEquals(1L, credenciales.getId());
        assertEquals("test@example.com", credenciales.getCorreoElectronico());
        assertEquals("password123", credenciales.getContrasena());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Credenciales credenciales = new Credenciales();

        // Assert
        assertNotNull(credenciales);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Credenciales credenciales = new Credenciales(1L, "test@example.com", "password123");

        // Assert
        assertEquals(1L, credenciales.getId());
        assertEquals("test@example.com", credenciales.getCorreoElectronico());
        assertEquals("password123", credenciales.getContrasena());
    }
}
