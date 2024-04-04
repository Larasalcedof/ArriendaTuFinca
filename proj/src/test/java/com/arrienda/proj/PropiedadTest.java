package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arrienda.proj.entity.Propiedad;

import java.math.BigDecimal;

class PropiedadTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Propiedad propiedad = new Propiedad();
        propiedad.setId(1L);
        propiedad.setNombre("Casa de playa");
        propiedad.setDescripcion("Una hermosa casa frente al mar");
        propiedad.setUbicacion("Playa del Sol");
        propiedad.setCostoArrendamiento(BigDecimal.valueOf(2000));
        propiedad.setDisponible(true);

        // Assert
        assertEquals(1L, propiedad.getId());
        assertEquals("Casa de playa", propiedad.getNombre());
        assertEquals("Una hermosa casa frente al mar", propiedad.getDescripcion());
        assertEquals("Playa del Sol", propiedad.getUbicacion());
        assertEquals(BigDecimal.valueOf(2000), propiedad.getCostoArrendamiento());
        assertTrue(propiedad.isDisponible());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Propiedad propiedad = new Propiedad();

        // Assert
        assertNotNull(propiedad);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Propiedad propiedad = new Propiedad(1L, "Casa de playa", "Una hermosa casa frente al mar",
                "Playa del Sol", BigDecimal.valueOf(2000), true, null);

        // Assert
        assertEquals(1L, propiedad.getId());
        assertEquals("Casa de playa", propiedad.getNombre());
        assertEquals("Una hermosa casa frente al mar", propiedad.getDescripcion());
        assertEquals("Playa del Sol", propiedad.getUbicacion());
        assertEquals(BigDecimal.valueOf(2000), propiedad.getCostoArrendamiento());
        assertTrue(propiedad.isDisponible());
    }
}
