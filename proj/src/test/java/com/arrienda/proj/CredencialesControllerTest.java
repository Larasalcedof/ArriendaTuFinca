package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.controllers.CalificacionController;
import com.arrienda.proj.controllers.CredencialesController;
import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.services.CalificacionService;
import com.arrienda.proj.services.CredencialesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CredencialesControllerTest {
    @Mock
    private CredencialesService service;
    @InjectMocks
    private CredencialesController controller;

    @Test
    void testConstructor() {
        // Arrange
        CalificacionService calificacionServiceMock = mock(CalificacionService.class);

        // Act
        CalificacionController controller = new CalificacionController(calificacionServiceMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void testCreateCredenciales() {
        // Arrange
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        CredencialesDTO createdCredencialesDTO = new CredencialesDTO();
        when(service.save(any(CredencialesDTO.class))).thenReturn(createdCredencialesDTO);
        // Act
        ResponseEntity<CredencialesDTO> responseEntity = controller.createCredenciales(credencialesDTO);
        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(createdCredencialesDTO, responseEntity.getBody());
        verify(service).save(credencialesDTO);
    }

    @Test
    void testGetAllCredenciales() {
        // Arrange
        List<CredencialesDTO> credencialesList = Arrays.asList(new CredencialesDTO(), new CredencialesDTO());
        when(service.findAll()).thenReturn(credencialesList);
        // Act
        ResponseEntity<List<CredencialesDTO>> responseEntity = controller.getAllCredenciales();
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(credencialesList, responseEntity.getBody());
        verify(service).findAll();
    }

    @Test
    void testGetCredencialesById() {
        // Arrange
        Long id = 1L;
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        when(service.findById(id)).thenReturn(credencialesDTO);
        // Act
        ResponseEntity<CredencialesDTO> responseEntity = controller.getCredencialesById(id);
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(credencialesDTO, responseEntity.getBody());
        verify(service).findById(id);
    }

    @Test
    void testGetCredencialesById_NotFound() {
        // Arrange
        Long id = 1L;
        when(service.findById(id)).thenReturn(null);
        // Act
        ResponseEntity<CredencialesDTO> responseEntity = controller.getCredencialesById(id);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(service).findById(id);
    }

    @Test
    void testUpdateCredenciales() {
        // Arrange
        Long id = 1L;
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        CredencialesDTO updatedCredencialesDTO = new CredencialesDTO();
        when(service.update(id, credencialesDTO)).thenReturn(updatedCredencialesDTO);
        // Act
        ResponseEntity<CredencialesDTO> responseEntity = controller.updateCredenciales(id, credencialesDTO);
        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedCredencialesDTO, responseEntity.getBody());
        verify(service).update(id, credencialesDTO);
    }

    @Test
    void testUpdateCredenciales_NotFound() {
        // Arrange
        Long id = 1L;
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        when(service.update(id, credencialesDTO)).thenReturn(null);
        // Act
        ResponseEntity<CredencialesDTO> responseEntity = controller.updateCredenciales(id, credencialesDTO);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(service).update(id, credencialesDTO);
    }

    @Test
    void testDeleteCredenciales() {
        // Arrange
        Long id = 1L;
        when(service.delete(id)).thenReturn(true);
        // Act
        ResponseEntity<Void> responseEntity = controller.deleteCredenciales(id);
        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(service).delete(id);
    }

    @Test
    void testDeleteCredenciales_NotFound() {
        // Arrange
        Long id = 1L;
        when(service.delete(id)).thenReturn(false);
        // Act
        ResponseEntity<Void> responseEntity = controller.deleteCredenciales(id);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(service).delete(id);
    }
}
