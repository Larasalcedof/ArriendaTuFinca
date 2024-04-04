package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arrienda.proj.entity.Calificacion;
import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.entity.Usuario;

@ExtendWith(MockitoExtension.class)
class CalificacionTest {

    @Mock
    private Usuario mockUsuario;

    @Mock
    private Propiedad mockPropiedad;

    @Test
    void testGettersAndSetters() {
        // Arrange
        Calificacion calificacion = new Calificacion();
        calificacion.setId(1L);
        calificacion.setValoracion(5);
        calificacion.setComentario("Great experience");
        calificacion.setFechaCalificacion("2024-04-04");
        calificacion.setUsuario(mockUsuario);
        calificacion.setPropiedad(mockPropiedad);

        // Assert
        assertEquals(1L, calificacion.getId());
        assertEquals(5, calificacion.getValoracion());
        assertEquals("Great experience", calificacion.getComentario());
        assertEquals("2024-04-04", calificacion.getFechaCalificacion());
        assertEquals(mockUsuario, calificacion.getUsuario());
        assertEquals(mockPropiedad, calificacion.getPropiedad());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Calificacion calificacion = new Calificacion();

        // Assert
        assertNotNull(calificacion);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Calificacion calificacion = new Calificacion(1L, 5, "Great experience", "2024-04-04", mockUsuario, mockPropiedad);

        // Assert
        assertEquals(1L, calificacion.getId());
        assertEquals(5, calificacion.getValoracion());
        assertEquals("Great experience", calificacion.getComentario());
        assertEquals("2024-04-04", calificacion.getFechaCalificacion());
        assertEquals(mockUsuario, calificacion.getUsuario());
        assertEquals(mockPropiedad, calificacion.getPropiedad());
    }
}
