package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.arrienda.proj.dto.CredencialesDTO;

@SpringBootTest
class CredencialesDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        com.arrienda.proj.entity.Credenciales credencialesEntity = new com.arrienda.proj.entity.Credenciales();
        credencialesEntity.setId(1L);
        credencialesEntity.setCorreoElectronico("test@example.com");
        credencialesEntity.setContrasena("password");

        // Act
        CredencialesDTO credencialesDTO = CredencialesDTO.fromEntity(credencialesEntity);

        // Assert
        assertNotNull(credencialesDTO);
        assertEquals(credencialesEntity.getId(), credencialesDTO.getId());
        assertEquals(credencialesEntity.getCorreoElectronico(), credencialesDTO.getCorreoElectronico());
        assertNull(credencialesDTO.getContrasena()); // La contraseña no se expone en el DTO
    }

    @Test
    void testToEntity() {
        // Arrange
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setId(1L);
        credencialesDTO.setCorreoElectronico("test@example.com");

        // Act
        com.arrienda.proj.entity.Credenciales credencialesEntity = credencialesDTO.toEntity();

        // Assert
        assertNotNull(credencialesEntity);
        assertEquals(credencialesDTO.getId(), credencialesEntity.getId());
        assertEquals(credencialesDTO.getCorreoElectronico(), credencialesEntity.getCorreoElectronico());
        assertNull(credencialesEntity.getContrasena()); // La contraseña no se expone en la entidad
    }
}

