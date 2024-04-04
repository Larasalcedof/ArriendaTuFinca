package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.dto.UsuarioDTO;

import java.math.BigDecimal;

@SpringBootTest
class PropiedadDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        com.arrienda.proj.entity.Propiedad propiedadEntity = new com.arrienda.proj.entity.Propiedad();
        propiedadEntity.setId(1L);
        propiedadEntity.setNombre("Casa de playa");
        propiedadEntity.setDescripcion("Hermosa casa frente al mar");
        propiedadEntity.setUbicacion("Playa del Carmen");
        propiedadEntity.setCostoArrendamiento(BigDecimal.valueOf(1000));
        propiedadEntity.setDisponible(true);
        CredencialesDTO credenciales = new CredencialesDTO();
        credenciales.setContrasena("123");
        credenciales.setCorreoElectronico("hola");
        credenciales.setId(1L);
        UsuarioDTO arrendadorDTO = new UsuarioDTO();
        arrendadorDTO.setCredenciales(credenciales);
        arrendadorDTO.setId(1L);
        propiedadEntity.setArrendador(arrendadorDTO.toEntity());

        // Act
        PropiedadDTO propiedadDTO = PropiedadDTO.fromEntity(propiedadEntity);

        // Assert
        assertNotNull(propiedadDTO);
        assertEquals(propiedadEntity.getId(), propiedadDTO.getId());
        assertEquals(propiedadEntity.getNombre(), propiedadDTO.getNombre());
        assertEquals(propiedadEntity.getDescripcion(), propiedadDTO.getDescripcion());
        assertEquals(propiedadEntity.getUbicacion(), propiedadDTO.getUbicacion());
        assertEquals(propiedadEntity.getCostoArrendamiento(), propiedadDTO.getCostoArrendamiento());
        assertEquals(propiedadEntity.isDisponible(), propiedadDTO.isDisponible());
        assertNotNull(propiedadDTO.getArrendador());
        assertEquals(propiedadEntity.getArrendador().getId(), propiedadDTO.getArrendador().getId());
    }

    @Test
    void testToEntity() {
        // Arrange
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        propiedadDTO.setNombre("Casa de playa");
        propiedadDTO.setDescripcion("Hermosa casa frente al mar");
        propiedadDTO.setUbicacion("Playa del Carmen");
        propiedadDTO.setCostoArrendamiento(BigDecimal.valueOf(1000));
        propiedadDTO.setDisponible(true);
        UsuarioDTO arrendadorDTO = new UsuarioDTO();
        arrendadorDTO.setId(1L);
        propiedadDTO.setArrendador(arrendadorDTO);

        // Act
        com.arrienda.proj.entity.Propiedad propiedadEntity = propiedadDTO.toEntity();

        // Assert
        assertNotNull(propiedadEntity);
        assertEquals(propiedadDTO.getId(), propiedadEntity.getId());
        assertEquals(propiedadDTO.getNombre(), propiedadEntity.getNombre());
        assertEquals(propiedadDTO.getDescripcion(), propiedadEntity.getDescripcion());
        assertEquals(propiedadDTO.getUbicacion(), propiedadEntity.getUbicacion());
        assertEquals(propiedadDTO.getCostoArrendamiento(), propiedadEntity.getCostoArrendamiento());
        assertEquals(propiedadDTO.isDisponible(), propiedadEntity.isDisponible());
        assertNotNull(propiedadEntity.getArrendador());
        assertEquals(propiedadDTO.getArrendador().getId(), propiedadEntity.getArrendador().getId());
    }
}

