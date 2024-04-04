package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.arrienda.proj.entity.Pago;

import java.math.BigDecimal;

class PagoTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setFechaPago("2024-04-04");
        pago.setMonto(BigDecimal.valueOf(1000));
        pago.setEstado(1);

        // Assert
        assertEquals(1L, pago.getId());
        assertEquals("2024-04-04", pago.getFechaPago());
        assertEquals(BigDecimal.valueOf(1000), pago.getMonto());
        assertEquals(1, pago.getEstado());
    }

    @Test
    void testNoArgsConstructor() {
        // Arrange
        Pago pago = new Pago();

        // Assert
        assertNotNull(pago);
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Pago pago = new Pago(1L, "2024-04-04", BigDecimal.valueOf(1000), 1, null, null);

        // Assert
        assertEquals(1L, pago.getId());
        assertEquals("2024-04-04", pago.getFechaPago());
        assertEquals(BigDecimal.valueOf(1000), pago.getMonto());
        assertEquals(1, pago.getEstado());
    }
}
