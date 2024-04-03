package com.arrienda.proj;

import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.services.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PagoTest {

    @Autowired
    private PagoService pagoService;

    @Test
    public void testSavePago() {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setFechaPago("2024-04-03");
        pagoDTO.setMonto(new BigDecimal("100.00"));
        pagoDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        PagoDTO savedPagoDTO = pagoService.save(pagoDTO);

        assertNotNull(savedPagoDTO.getId());
        assertEquals(pagoDTO.getFechaPago(), savedPagoDTO.getFechaPago());
        assertEquals(pagoDTO.getMonto(), savedPagoDTO.getMonto());
        assertEquals(pagoDTO.getEstado(), savedPagoDTO.getEstado());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindAllPagos() {
        List<PagoDTO> pagosDTOList = pagoService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(pagosDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdPago() {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setFechaPago("2024-04-03");
        pagoDTO.setMonto(new BigDecimal("100.00"));
        pagoDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        PagoDTO savedPagoDTO = pagoService.save(pagoDTO);
        Long id = savedPagoDTO.getId();

        PagoDTO foundPagoDTO = pagoService.findById(id);

        assertNotNull(foundPagoDTO);
        assertEquals(pagoDTO.getFechaPago(), foundPagoDTO.getFechaPago());
        assertEquals(pagoDTO.getMonto(), foundPagoDTO.getMonto());
        assertEquals(pagoDTO.getEstado(), foundPagoDTO.getEstado());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testUpdatePago() {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setFechaPago("2024-04-03");
        pagoDTO.setMonto(new BigDecimal("100.00"));
        pagoDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        PagoDTO savedPagoDTO = pagoService.save(pagoDTO);
        Long id = savedPagoDTO.getId();

        pagoDTO.setMonto(new BigDecimal("150.00"));
        PagoDTO updatedPagoDTO = pagoService.update(id, pagoDTO);

        assertNotNull(updatedPagoDTO);
        assertEquals(new BigDecimal("150.00"), updatedPagoDTO.getMonto());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testDeletePago() {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setFechaPago("2024-04-03");
        pagoDTO.setMonto(new BigDecimal("100.00"));
        pagoDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        PagoDTO savedPagoDTO = pagoService.save(pagoDTO);
        Long id = savedPagoDTO.getId();

        boolean isDeleted = pagoService.delete(id);

        assertEquals(true, isDeleted);
    }
}
