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
import com.arrienda.proj.controllers.SolicitudController;
import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.services.SolicitudService;

class SolicitudControllerTest {

    @Test
    void testConstructor() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);

        // Act
        SolicitudController controller = new SolicitudController(solicitudServiceMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void testGetAllSolicitudes() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);
        SolicitudController controller = new SolicitudController(solicitudServiceMock);
        List<SolicitudDTO> solicitudesList = Arrays.asList(new SolicitudDTO(), new SolicitudDTO());
        when(solicitudServiceMock.findAll()).thenReturn(solicitudesList);

        // Act
        List<SolicitudDTO> result = controller.getAllSolicitudes();

        // Assert
        assertEquals(solicitudesList.size(), result.size());
        verify(solicitudServiceMock).findAll();
    }

    @Test
    void testGetSolicitudById() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);
        SolicitudController controller = new SolicitudController(solicitudServiceMock);
        Long id = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        when(solicitudServiceMock.findById(id)).thenReturn(solicitudDTO);

        // Act
        SolicitudDTO result = controller.getSolicitudById(id);

        // Assert
        assertNotNull(result);
        verify(solicitudServiceMock).findById(id);
    }

    @Test
    void testCreateSolicitud() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);
        SolicitudController controller = new SolicitudController(solicitudServiceMock);
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        SolicitudDTO createdSolicitudDTO = new SolicitudDTO();
        when(solicitudServiceMock.save(any(SolicitudDTO.class))).thenReturn(createdSolicitudDTO);

        // Act
        SolicitudDTO result = controller.createSolicitud(solicitudDTO);

        // Assert
        assertNotNull(result);
        assertEquals(createdSolicitudDTO, result);
        verify(solicitudServiceMock).save(solicitudDTO);
    }

    @Test
    void testUpdateSolicitud() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);
        SolicitudController controller = new SolicitudController(solicitudServiceMock);
        Long id = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        SolicitudDTO updatedSolicitudDTO = new SolicitudDTO();
        when(solicitudServiceMock.update(id, solicitudDTO)).thenReturn(updatedSolicitudDTO);

        // Act
        SolicitudDTO result = controller.updateSolicitud(id, solicitudDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedSolicitudDTO, result);
        verify(solicitudServiceMock).update(id, solicitudDTO);
    }

    @Test
    void testDeleteSolicitud() {
        // Arrange
        SolicitudService solicitudServiceMock = mock(SolicitudService.class);
        SolicitudController controller = new SolicitudController(solicitudServiceMock);
        Long id = 1L;

        // Act
        controller.deleteSolicitud(id);

        // Assert
        verify(solicitudServiceMock).delete(id);
    }
}
