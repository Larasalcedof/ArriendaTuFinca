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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arrienda.proj.controllers.UsuarioController;
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.services.UsuarioService;

class UsuarioControllerTest {

    @Test
    void testConstructor() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);

        // Act
        UsuarioController controller = new UsuarioController(usuarioServiceMock);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void testGetAllUsuarios() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        List<UsuarioDTO> usuariosList = Arrays.asList(new UsuarioDTO(), new UsuarioDTO());
        when(usuarioServiceMock.findAll()).thenReturn(usuariosList);

        // Act
        ResponseEntity<List<UsuarioDTO>> result = controller.getAllUsuarios();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuariosList, result.getBody());
        verify(usuarioServiceMock).findAll();
    }

    @Test
    void testGetUsuarioById() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioServiceMock.findById(id)).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> result = controller.getUsuarioById(id);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuarioDTO, result.getBody());
        verify(usuarioServiceMock).findById(id);
    }

    @Test
    void testGetUsuarioById_NotFound() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        when(usuarioServiceMock.findById(id)).thenReturn(null);

        // Act
        ResponseEntity<UsuarioDTO> result = controller.getUsuarioById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(usuarioServiceMock).findById(id);
    }

    @Test
    void testCreateUsuario() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO createdUsuarioDTO = new UsuarioDTO();
        when(usuarioServiceMock.save(any(UsuarioDTO.class))).thenReturn(createdUsuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> result = controller.createUsuario(usuarioDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdUsuarioDTO, result.getBody());
        verify(usuarioServiceMock).save(usuarioDTO);
    }

    @Test
    void testUpdateUsuario() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO updatedUsuarioDTO = new UsuarioDTO();
        when(usuarioServiceMock.update(id, usuarioDTO)).thenReturn(updatedUsuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> result = controller.updateUsuario(id, usuarioDTO);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedUsuarioDTO, result.getBody());
        verify(usuarioServiceMock).update(id, usuarioDTO);
    }

    @Test
    void testUpdateUsuario_NotFound() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioServiceMock.update(id, usuarioDTO)).thenReturn(null);

        // Act
        ResponseEntity<UsuarioDTO> result = controller.updateUsuario(id, usuarioDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(usuarioServiceMock).update(id, usuarioDTO);
    }

    @Test
    void testDeleteUsuario() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        when(usuarioServiceMock.delete(id)).thenReturn(true);

        // Act
        ResponseEntity<Void> result = controller.deleteUsuario(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(usuarioServiceMock).delete(id);
    }

    @Test
    void testDeleteUsuario_NotFound() {
        // Arrange
        UsuarioService usuarioServiceMock = mock(UsuarioService.class);
        UsuarioController controller = new UsuarioController(usuarioServiceMock);
        Long id = 1L;
        when(usuarioServiceMock.delete(id)).thenReturn(false);

        // Act
        ResponseEntity<Void> result = controller.deleteUsuario(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(usuarioServiceMock).delete(id);
    }
}

