package com.arrienda.proj;

import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.services.PropiedadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PropiedadTest {

    @Autowired
    private PropiedadService propiedadService;

    @Test
    public void testSavePropiedad() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setNombre("Casa de playa");
        propiedadDTO.setDescripcion("Hermosa casa con vista al mar");
        propiedadDTO.setUbicacion("Playa del Carmen");
        propiedadDTO.setCostoArrendamiento(new BigDecimal("2000.00"));
        propiedadDTO.setDisponible(true);
        // Suponiendo que existen los métodos para obtener el DTO de arrendador

        PropiedadDTO savedPropiedadDTO = propiedadService.save(propiedadDTO);

        assertNotNull(savedPropiedadDTO.getId());
        assertEquals(propiedadDTO.getNombre(), savedPropiedadDTO.getNombre());
        assertEquals(propiedadDTO.getDescripcion(), savedPropiedadDTO.getDescripcion());
        assertEquals(propiedadDTO.getUbicacion(), savedPropiedadDTO.getUbicacion());
        assertEquals(propiedadDTO.getCostoArrendamiento(), savedPropiedadDTO.getCostoArrendamiento());
        assertEquals(propiedadDTO.isDisponible(), savedPropiedadDTO.isDisponible());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindAllPropiedades() {
        List<PropiedadDTO> propiedadesDTOList = propiedadService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(propiedadesDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdPropiedad() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setNombre("Casa de playa");
        propiedadDTO.setDescripcion("Hermosa casa con vista al mar");
        propiedadDTO.setUbicacion("Playa del Carmen");
        propiedadDTO.setCostoArrendamiento(new BigDecimal("2000.00"));
        propiedadDTO.setDisponible(true);
        // Suponiendo que existen los métodos para obtener el DTO de arrendador

        PropiedadDTO savedPropiedadDTO = propiedadService.save(propiedadDTO);
        Long id = savedPropiedadDTO.getId();

        PropiedadDTO foundPropiedadDTO = propiedadService.findById(id);

        assertNotNull(foundPropiedadDTO);
        assertEquals(propiedadDTO.getNombre(), foundPropiedadDTO.getNombre());
        assertEquals(propiedadDTO.getDescripcion(), foundPropiedadDTO.getDescripcion());
        assertEquals(propiedadDTO.getUbicacion(), foundPropiedadDTO.getUbicacion());
        assertEquals(propiedadDTO.getCostoArrendamiento(), foundPropiedadDTO.getCostoArrendamiento());
        assertEquals(propiedadDTO.isDisponible(), foundPropiedadDTO.isDisponible());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testUpdatePropiedad() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setNombre("Casa de playa");
        propiedadDTO.setDescripcion("Hermosa casa con vista al mar");
        propiedadDTO.setUbicacion("Playa del Carmen");
        propiedadDTO.setCostoArrendamiento(new BigDecimal("2000.00"));
        propiedadDTO.setDisponible(true);
        // Suponiendo que existen los métodos para obtener el DTO de arrendador

        PropiedadDTO savedPropiedadDTO = propiedadService.save(propiedadDTO);
        Long id = savedPropiedadDTO.getId();

        propiedadDTO.setCostoArrendamiento(new BigDecimal("2500.00"));
        PropiedadDTO updatedPropiedadDTO = propiedadService.update(id, propiedadDTO);

        assertNotNull(updatedPropiedadDTO);
        assertEquals(new BigDecimal("2500.00"), updatedPropiedadDTO.getCostoArrendamiento());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testDeletePropiedad() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setNombre("Casa de playa");
        propiedadDTO.setDescripcion("Hermosa casa con vista al mar");
        propiedadDTO.setUbicacion("Playa del Carmen");
        propiedadDTO.setCostoArrendamiento(new BigDecimal("2000.00"));
        propiedadDTO.setDisponible(true);
        // Suponiendo que existen los métodos para obtener el DTO de arrendador

        PropiedadDTO savedPropiedadDTO = propiedadService.save(propiedadDTO);
        Long id = savedPropiedadDTO.getId();

        boolean isDeleted = propiedadService.delete(id);

        assertEquals(true, isDeleted);
    }
}
