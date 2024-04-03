package com.arrienda.proj;

import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.services.SolicitudService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SolicitudTest {

    @Autowired
    private SolicitudService solicitudService;

    @Test
    public void testSaveSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setFechaSolicitud(LocalDateTime.now().toString());
        solicitudDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener el DTO de arrendatario y propiedad

        SolicitudDTO savedSolicitudDTO = solicitudService.save(solicitudDTO);

        assertNotNull(savedSolicitudDTO.getId());
        assertEquals(solicitudDTO.getFechaSolicitud(), savedSolicitudDTO.getFechaSolicitud());
        assertEquals(solicitudDTO.getEstado(), savedSolicitudDTO.getEstado());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindAllSolicitudes() {
        List<SolicitudDTO> solicitudesDTOList = solicitudService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(solicitudesDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setFechaSolicitud(LocalDateTime.now().toString());
        solicitudDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener el DTO de arrendatario y propiedad

        SolicitudDTO savedSolicitudDTO = solicitudService.save(solicitudDTO);
        Long id = savedSolicitudDTO.getId();

        SolicitudDTO foundSolicitudDTO = solicitudService.findById(id);

        assertNotNull(foundSolicitudDTO);
        assertEquals(solicitudDTO.getFechaSolicitud(), foundSolicitudDTO.getFechaSolicitud());
        assertEquals(solicitudDTO.getEstado(), foundSolicitudDTO.getEstado());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testUpdateSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setFechaSolicitud(LocalDateTime.now().toString());
        solicitudDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener el DTO de arrendatario y propiedad

        SolicitudDTO savedSolicitudDTO = solicitudService.save(solicitudDTO);
        Long id = savedSolicitudDTO.getId();

        solicitudDTO.setEstado(2);
        SolicitudDTO updatedSolicitudDTO = solicitudService.update(id, solicitudDTO);

        assertNotNull(updatedSolicitudDTO);
        assertEquals(2, updatedSolicitudDTO.getEstado());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testDeleteSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setFechaSolicitud(LocalDateTime.now().toString());
        solicitudDTO.setEstado(1);
        // Suponiendo que existen los métodos para obtener el DTO de arrendatario y propiedad

        SolicitudDTO savedSolicitudDTO = solicitudService.save(solicitudDTO);
        Long id = savedSolicitudDTO.getId();

        boolean isDeleted = solicitudService.delete(id);

        assertEquals(true, isDeleted);
    }
}
