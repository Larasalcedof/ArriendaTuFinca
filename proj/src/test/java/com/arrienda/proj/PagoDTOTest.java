package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.dto.UsuarioDTO;

import java.math.BigDecimal;

@SpringBootTest
class PagoDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        com.arrienda.proj.entity.Pago pagoEntity = new com.arrienda.proj.entity.Pago();
        pagoEntity.setId(1L);
        pagoEntity.setFechaPago("2024-04-04");
        pagoEntity.setMonto(BigDecimal.valueOf(500));
        pagoEntity.setEstado(1);
        CredencialesDTO credenciales = new CredencialesDTO();
        credenciales.setContrasena("123");
        credenciales.setCorreoElectronico("hola");
        credenciales.setId(1L);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setCredenciales(credenciales);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        pagoEntity.setUsuario(usuarioDTO.toEntity());
        pagoEntity.setPropiedad(propiedadDTO.toEntity());

        // Act
        PagoDTO pagoDTO = PagoDTO.fromEntity(pagoEntity);

        // Assert
        assertNotNull(pagoDTO);
        assertEquals(pagoEntity.getId(), pagoDTO.getId());
        assertEquals(pagoEntity.getFechaPago(), pagoDTO.getFechaPago());
        assertEquals(pagoEntity.getMonto(), pagoDTO.getMonto());
        assertEquals(pagoEntity.getEstado(), pagoDTO.getEstado());
        assertNotNull(pagoDTO.getUsuario());
        assertEquals(pagoEntity.getUsuario().getId(), pagoDTO.getUsuario().getId());
        assertNotNull(pagoDTO.getPropiedad());
        assertEquals(pagoEntity.getPropiedad().getId(), pagoDTO.getPropiedad().getId());
    }

    @Test
    void testToEntity() {
        // Arrange
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId(1L);
        pagoDTO.setFechaPago("2024-04-04");
        pagoDTO.setMonto(BigDecimal.valueOf(500));
        pagoDTO.setEstado(1);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        pagoDTO.setUsuario(usuarioDTO);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        pagoDTO.setPropiedad(propiedadDTO);

        // Act
        com.arrienda.proj.entity.Pago pagoEntity = pagoDTO.toEntity();

        // Assert
        assertNotNull(pagoEntity);
        assertEquals(pagoDTO.getId(), pagoEntity.getId());
        assertEquals(pagoDTO.getFechaPago(), pagoEntity.getFechaPago());
        assertEquals(pagoDTO.getMonto(), pagoEntity.getMonto());
        assertEquals(pagoDTO.getEstado(), pagoEntity.getEstado());
        assertNotNull(pagoEntity.getUsuario());
        assertEquals(pagoDTO.getUsuario().getId(), pagoEntity.getUsuario().getId());
        assertNotNull(pagoEntity.getPropiedad());
        assertEquals(pagoDTO.getPropiedad().getId(), pagoEntity.getPropiedad().getId());
    }
}

