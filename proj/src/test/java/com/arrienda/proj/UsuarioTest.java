package com.arrienda.proj;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void testSaveUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Nombre de usuario");
        usuarioDTO.setRol(1);
        
        // Crea y configura las credenciales
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("username");
        credencialesDTO.setContrasena("password");
        usuarioDTO.setCredenciales(credencialesDTO);

        UsuarioDTO savedUsuarioDTO = usuarioService.save(usuarioDTO);

        assertNotNull(savedUsuarioDTO.getId());
        assertEquals(usuarioDTO.getNombre(), savedUsuarioDTO.getNombre());
        assertEquals(usuarioDTO.getRol(), savedUsuarioDTO.getRol());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindAllUsuarios() {
        List<UsuarioDTO> usuariosDTOList = usuarioService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(usuariosDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Nombre de usuario");
        usuarioDTO.setRol(1);
        
        // Crea y configura las credenciales
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("username");
        credencialesDTO.setContrasena("password");
        usuarioDTO.setCredenciales(credencialesDTO);

        UsuarioDTO savedUsuarioDTO = usuarioService.save(usuarioDTO);
        Long id = savedUsuarioDTO.getId();

        UsuarioDTO foundUsuarioDTO = usuarioService.findById(id);

        assertNotNull(foundUsuarioDTO);
        assertEquals(usuarioDTO.getNombre(), foundUsuarioDTO.getNombre());
        assertEquals(usuarioDTO.getRol(), foundUsuarioDTO.getRol());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testUpdateUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Nombre de usuario");
        usuarioDTO.setRol(1);
        
        // Crea y configura las credenciales
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("username");
        credencialesDTO.setContrasena("password");
        usuarioDTO.setCredenciales(credencialesDTO);

        UsuarioDTO savedUsuarioDTO = usuarioService.save(usuarioDTO);
        Long id = savedUsuarioDTO.getId();

        usuarioDTO.setNombre("Nuevo nombre de usuario");
        UsuarioDTO updatedUsuarioDTO = usuarioService.update(id, usuarioDTO);

        assertNotNull(updatedUsuarioDTO);
        assertEquals("Nuevo nombre de usuario", updatedUsuarioDTO.getNombre());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testDeleteUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Nombre de usuario");
        usuarioDTO.setRol(1);
        
        // Crea y configura las credenciales
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("username");
        credencialesDTO.setContrasena("password");
        usuarioDTO.setCredenciales(credencialesDTO);

        UsuarioDTO savedUsuarioDTO = usuarioService.save(usuarioDTO);
        Long id = savedUsuarioDTO.getId();

        boolean isDeleted = usuarioService.delete(id);

        assertEquals(true, isDeleted);
    }
}
