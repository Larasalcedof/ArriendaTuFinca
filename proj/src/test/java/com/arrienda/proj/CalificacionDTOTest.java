package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CalificacionDTO;
import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.entity.Calificacion;
import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.entity.Usuario;

@SpringBootTest
class CalificacionDTOTest {

    @Test
    void testFromEntity() {
        // Arrange
        Calificacion calificacion = new Calificacion();
        calificacion.setId(1L);
        calificacion.setValoracion(4);
        calificacion.setComentario("Good experience");
        calificacion.setFechaCalificacion("2024-04-04");
        Usuario usuario = new Usuario();
        Credenciales credenciales = new Credenciales();
        credenciales.setContrasena("123");
        credenciales.setCorreoElectronico("hola");
        credenciales.setId(1L);
        usuario.setId(1L);
        usuario.setCredenciales(credenciales);
        calificacion.setUsuario(usuario);
        Propiedad propiedad = new Propiedad();
        propiedad.setId(1L);
        calificacion.setPropiedad(propiedad);

        // Act
        CalificacionDTO calificacionDTO = CalificacionDTO.fromEntity(calificacion);

        // Assert
        assertNotNull(calificacionDTO);
        assertEquals(calificacion.getId(), calificacionDTO.getId());
        assertEquals(calificacion.getValoracion(), calificacionDTO.getValoracion());
        assertEquals(calificacion.getComentario(), calificacionDTO.getComentario());
        assertEquals(calificacion.getFechaCalificacion(), calificacionDTO.getFechaCalificacion());
        assertNotNull(calificacionDTO.getUsuario());
        assertEquals(calificacion.getUsuario().getId(), calificacionDTO.getUsuario().getId());
        assertNotNull(calificacionDTO.getPropiedad());
        assertEquals(calificacion.getPropiedad().getId(), calificacionDTO.getPropiedad().getId());
    }

    @Test
    void testToEntity() {
        // Arrange
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setId(1L);
        calificacionDTO.setValoracion(4);
        calificacionDTO.setComentario("Good experience");
        calificacionDTO.setFechaCalificacion("2024-04-04");
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        calificacionDTO.setUsuario(usuarioDTO);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(1L);
        calificacionDTO.setPropiedad(propiedadDTO);

        // Act
        Calificacion calificacion = calificacionDTO.toEntity();

        // Assert
        assertNotNull(calificacion);
        assertEquals(calificacionDTO.getId(), calificacion.getId());
        assertEquals(calificacionDTO.getValoracion(), calificacion.getValoracion());
        assertEquals(calificacionDTO.getComentario(), calificacion.getComentario());
        assertEquals(calificacionDTO.getFechaCalificacion(), calificacion.getFechaCalificacion());
        assertNotNull(calificacion.getUsuario());
        assertEquals(calificacionDTO.getUsuario().getId(), calificacion.getUsuario().getId());
        assertNotNull(calificacion.getPropiedad());
        assertEquals(calificacionDTO.getPropiedad().getId(), calificacion.getPropiedad().getId());
    }
}
