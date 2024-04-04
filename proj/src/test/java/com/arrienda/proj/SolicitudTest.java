package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.entity.Solicitud;
import com.arrienda.proj.entity.Usuario;

class SolicitudTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setFechaSolicitud("2024-04-04");
        solicitud.setEstado(1);

        // Assert
        assertEquals(1L, solicitud.getId());
        assertEquals("2024-04-04", solicitud.getFechaSolicitud());
        assertEquals(1, solicitud.getEstado());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Solicitud solicitud = new Solicitud();

        // Assert
        assertNotNull(solicitud);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Usuario arrendatario = new Usuario();
        Propiedad propiedad = new Propiedad();
        Solicitud solicitud = new Solicitud(1L, "2024-04-04", 1, arrendatario, propiedad);

        // Assert
        assertEquals(1L, solicitud.getId());
        assertEquals("2024-04-04", solicitud.getFechaSolicitud());
        assertEquals(1, solicitud.getEstado());
        assertEquals(arrendatario, solicitud.getArrendatario());
        assertEquals(propiedad, solicitud.getPropiedad());
    }
}
