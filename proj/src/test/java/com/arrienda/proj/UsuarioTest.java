package com.arrienda.proj;

import com.arrienda.proj.controllers.UsuarioController;
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UsuarioTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsuarios() {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        // Simulamos que el servicio devuelve una lista de usuarios
        when(usuarioService.findAll()).thenReturn(usuarios);
    
        // Llamamos al método del controlador que queremos probar
        ResponseEntity<List<UsuarioDTO>> response = usuarioController.getAllUsuarios();
    
        // Verificamos que se haya devuelto un estado HTTP OK y que la lista de usuarios sea la esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    
        // Imprimimos información en pantalla para verificar
        System.out.println("Usuarios recuperados: " + usuarios.size());
    }
    
    @Test
    public void testGetUsuarioById() {
        Long id = 7L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(id);
        // Simulamos que el servicio devuelve un usuario con el ID especificado
        when(usuarioService.findById(id)).thenReturn(usuarioDTO);
    
        // Llamamos al método del controlador que queremos probar
        ResponseEntity<UsuarioDTO> response = usuarioController.getUsuarioById(id);
    
        // Verificamos que se haya devuelto un estado HTTP OK y que el usuario devuelto sea el esperado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    
        // Imprimimos información en pantalla para verificar
        System.out.println("Usuario recuperado: " + usuarioDTO);
    }
    

    @Test
    public void testCreateUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.save(usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.createUsuario(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    }

    @Test
    public void testUpdateUsuario() {
        Long id = 1L;
        UsuarioDTO updatedUsuarioDTO = new UsuarioDTO();
        updatedUsuarioDTO.setId(id);
        when(usuarioService.update(id, updatedUsuarioDTO)).thenReturn(updatedUsuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.updateUsuario(id, updatedUsuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUsuarioDTO, response.getBody());
    }

    @Test
    public void testDeleteUsuario() {
        Long id = 1L;
        when(usuarioService.delete(id)).thenReturn(true);

        ResponseEntity<Void> response = usuarioController.deleteUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody() == null);
    }

}
