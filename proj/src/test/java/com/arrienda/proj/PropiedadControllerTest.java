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
import com.arrienda.proj.controllers.PropiedadController;
import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.services.PropiedadService;

class PropiedadControllerTest {

    @Test
    void testConstructor() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);

        // Act
        PropiedadController controller = new PropiedadController(propiedadServiceMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void testGetAllPropiedades() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);
        PropiedadController controller = new PropiedadController(propiedadServiceMock);
        List<PropiedadDTO> propiedadesList = Arrays.asList(new PropiedadDTO(), new PropiedadDTO());
        when(propiedadServiceMock.findAll()).thenReturn(propiedadesList);

        // Act
        List<PropiedadDTO> result = controller.getAllPropiedades();

        // Assert
        assertEquals(propiedadesList.size(), result.size());
        verify(propiedadServiceMock).findAll();
    }

    @Test
    void testGetPropiedadById() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);
        PropiedadController controller = new PropiedadController(propiedadServiceMock);
        Long id = 1L;
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        when(propiedadServiceMock.findById(id)).thenReturn(propiedadDTO);

        // Act
        PropiedadDTO result = controller.getPropiedadById(id);

        // Assert
        assertNotNull(result);
        verify(propiedadServiceMock).findById(id);
    }

    @Test
    void testCreatePropiedad() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);
        PropiedadController controller = new PropiedadController(propiedadServiceMock);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        PropiedadDTO createdPropiedadDTO = new PropiedadDTO();
        when(propiedadServiceMock.save(any(PropiedadDTO.class))).thenReturn(createdPropiedadDTO);

        // Act
        PropiedadDTO result = controller.createPropiedad(propiedadDTO);

        // Assert
        assertNotNull(result);
        assertEquals(createdPropiedadDTO, result);
        verify(propiedadServiceMock).save(propiedadDTO);
    }

    @Test
    void testUpdatePropiedad() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);
        PropiedadController controller = new PropiedadController(propiedadServiceMock);
        Long id = 1L;
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        PropiedadDTO updatedPropiedadDTO = new PropiedadDTO();
        when(propiedadServiceMock.update(id, propiedadDTO)).thenReturn(updatedPropiedadDTO);

        // Act
        PropiedadDTO result = controller.updatePropiedad(id, propiedadDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedPropiedadDTO, result);
        verify(propiedadServiceMock).update(id, propiedadDTO);
    }

    @Test
    void testDeletePropiedad() {
        // Arrange
        PropiedadService propiedadServiceMock = mock(PropiedadService.class);
        PropiedadController controller = new PropiedadController(propiedadServiceMock);
        Long id = 1L;

        // Act
        controller.deletePropiedad(id);

        // Assert
        verify(propiedadServiceMock).delete(id);
    }
}
