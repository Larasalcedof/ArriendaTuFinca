package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.dto.UsuarioDTO;

@SpringBootTest
class SolicitudDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        com.arrienda.proj.entity.Solicitud solicitudEntity = new com.arrienda.proj.entity.Solicitud();
        solicitudEntity.setId(1L);
        solicitudEntity.setFechaSolicitud("2024-04-04");
        solicitudEntity.setEstado(1);
        CredencialesDTO credenciales = new CredencialesDTO();
        credenciales.setContrasena("123");
        credenciales.setCorreoElectronico("hola");
        credenciales.setId(1L);
        UsuarioDTO arrendatarioDTO = new UsuarioDTO();
        arrendatarioDTO.setId(1L);
        arrendatarioDTO.setCredenciales(credenciales);
        solicitudEntity.setArrendatario(arrendatarioDTO.toEntity());
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        solicitudEntity.setPropiedad(propiedadDTO.toEntity());

        // Act
        SolicitudDTO solicitudDTO = SolicitudDTO.fromEntity(solicitudEntity);

        // Assert
        assertNotNull(solicitudDTO);
        assertEquals(solicitudEntity.getId(), solicitudDTO.getId());
        assertEquals(solicitudEntity.getFechaSolicitud(), solicitudDTO.getFechaSolicitud());
        assertEquals(solicitudEntity.getEstado(), solicitudDTO.getEstado());
        assertNotNull(solicitudDTO.getArrendatario());
        assertEquals(solicitudEntity.getArrendatario().getId(), solicitudDTO.getArrendatario().getId());
        assertNotNull(solicitudDTO.getPropiedad());
        assertEquals(solicitudEntity.getPropiedad().getId(), solicitudDTO.getPropiedad().getId());
    }

    @Test
    void testToEntity() {
        // Arrange
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setId(1L);
        solicitudDTO.setFechaSolicitud("2024-04-04");
        solicitudDTO.setEstado(1);
        UsuarioDTO arrendatarioDTO = new UsuarioDTO();
        arrendatarioDTO.setId(1L);
        solicitudDTO.setArrendatario(arrendatarioDTO);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        solicitudDTO.setPropiedad(propiedadDTO);

        // Act
        com.arrienda.proj.entity.Solicitud solicitudEntity = solicitudDTO.toEntity();

        // Assert
        assertNotNull(solicitudEntity);
        assertEquals(solicitudDTO.getId(), solicitudEntity.getId());
        assertEquals(solicitudDTO.getFechaSolicitud(), solicitudEntity.getFechaSolicitud());
        assertEquals(solicitudDTO.getEstado(), solicitudEntity.getEstado());
        assertNotNull(solicitudEntity.getArrendatario());
        assertEquals(solicitudDTO.getArrendatario().getId(), solicitudEntity.getArrendatario().getId());
        assertNotNull(solicitudEntity.getPropiedad());
        assertEquals(solicitudDTO.getPropiedad().getId(), solicitudEntity.getPropiedad().getId());
    }
}

