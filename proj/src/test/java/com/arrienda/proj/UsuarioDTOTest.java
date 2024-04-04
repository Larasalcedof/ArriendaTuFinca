package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.UsuarioDTO;

@SpringBootTest
class UsuarioDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        com.arrienda.proj.entity.Usuario usuarioEntity = new com.arrienda.proj.entity.Usuario();
        usuarioEntity.setId(1L);
        usuarioEntity.setNombre("John Doe");
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setId(1L);
        usuarioEntity.setCredenciales(credencialesDTO.toEntity());
        usuarioEntity.setRol(1);

        // Act
        UsuarioDTO usuarioDTO = UsuarioDTO.fromEntity(usuarioEntity);

        // Assert
        assertNotNull(usuarioDTO);
        assertEquals(usuarioEntity.getId(), usuarioDTO.getId());
        assertEquals(usuarioEntity.getNombre(), usuarioDTO.getNombre());
        assertNotNull(usuarioDTO.getCredenciales());
        assertEquals(usuarioEntity.getCredenciales().getId(), usuarioDTO.getCredenciales().getId());
        assertEquals(usuarioEntity.getRol(), usuarioDTO.getRol());
    }

    @Test
    void testToEntity() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("John Doe");
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setId(1L);
        credencialesDTO.setContrasena("123");
        credencialesDTO.setCorreoElectronico("hola");
        usuarioDTO.setCredenciales(credencialesDTO);
        usuarioDTO.setRol(1);

        // Act
        com.arrienda.proj.entity.Usuario usuarioEntity = usuarioDTO.toEntity();

        // Assert
        assertNotNull(usuarioEntity);
        assertEquals(usuarioDTO.getId(), usuarioEntity.getId());
        assertEquals(usuarioDTO.getNombre(), usuarioEntity.getNombre());
        assertNotNull(usuarioEntity.getCredenciales());
        assertEquals(usuarioDTO.getCredenciales().getId(), usuarioEntity.getCredenciales().getId());
        assertEquals(usuarioDTO.getRol(), usuarioEntity.getRol());
    }
}
