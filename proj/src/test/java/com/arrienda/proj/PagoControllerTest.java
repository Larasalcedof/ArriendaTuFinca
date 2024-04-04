package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.arrienda.proj.controllers.PagoController;
import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.services.PagoService;

class PagoControllerTest {

    @Test
    void testConstructor() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);

        // Act
        PagoController controller = new PagoController(pagoServiceMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void testGetAllPagos() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);
        PagoController controller = new PagoController(pagoServiceMock);
        List<PagoDTO> pagosList = Arrays.asList(new PagoDTO(), new PagoDTO());
        when(pagoServiceMock.findAll()).thenReturn(pagosList);

        // Act
        List<PagoDTO> result = controller.getAllPagos();

        // Assert
        assertEquals(pagosList.size(), result.size());
        verify(pagoServiceMock).findAll();
    }

    @Test
    void testGetPagoById() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);
        PagoController controller = new PagoController(pagoServiceMock);
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        when(pagoServiceMock.findById(id)).thenReturn(pagoDTO);

        // Act
        PagoDTO result = controller.getPagoById(id);

        // Assert
        assertNotNull(result);
        verify(pagoServiceMock).findById(id);
    }

    @Test
    void testCreatePago() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);
        PagoController controller = new PagoController(pagoServiceMock);
        PagoDTO pagoDTO = new PagoDTO();
        PagoDTO createdPagoDTO = new PagoDTO();
        when(pagoServiceMock.save(any(PagoDTO.class))).thenReturn(createdPagoDTO);

        // Act
        PagoDTO result = controller.createPago(pagoDTO);

        // Assert
        assertNotNull(result);
        assertEquals(createdPagoDTO, result);
        verify(pagoServiceMock).save(pagoDTO);
    }

    @Test
    void testUpdatePago() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);
        PagoController controller = new PagoController(pagoServiceMock);
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        PagoDTO updatedPagoDTO = new PagoDTO();
        when(pagoServiceMock.update(id, pagoDTO)).thenReturn(updatedPagoDTO);

        // Act
        PagoDTO result = controller.updatePago(id, pagoDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedPagoDTO, result);
        verify(pagoServiceMock).update(id, pagoDTO);
    }

    @Test
    void testDeletePago() {
        // Arrange
        PagoService pagoServiceMock = mock(PagoService.class);
        PagoController controller = new PagoController(pagoServiceMock);
        Long id = 1L;

        // Act
        controller.deletePago(id);

        // Assert
        verify(pagoServiceMock).delete(id);
    }
}

