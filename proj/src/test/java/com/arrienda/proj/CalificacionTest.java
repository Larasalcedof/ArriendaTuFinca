package com.arrienda.proj;

import com.arrienda.proj.dto.CalificacionDTO;
import com.arrienda.proj.services.CalificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CalificacionTest {

    @Autowired
    private CalificacionService calificacionService;

    @Test
    public void testSaveCalificacion() {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setValoracion(5);
        calificacionDTO.setComentario("Excelente servicio");
        calificacionDTO.setFechaCalificacion("2024-04-03");
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        CalificacionDTO savedCalificacionDTO = calificacionService.save(calificacionDTO);

        assertNotNull(savedCalificacionDTO.getId());
        assertEquals(calificacionDTO.getValoracion(), savedCalificacionDTO.getValoracion());
        assertEquals(calificacionDTO.getComentario(), savedCalificacionDTO.getComentario());
        assertEquals(calificacionDTO.getFechaCalificacion(), savedCalificacionDTO.getFechaCalificacion());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindAllCalificaciones() {
        List<CalificacionDTO> calificacionesDTOList = calificacionService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(calificacionesDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdCalificacion() {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setValoracion(5);
        calificacionDTO.setComentario("Excelente servicio");
        calificacionDTO.setFechaCalificacion("2024-04-03");
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        CalificacionDTO savedCalificacionDTO = calificacionService.save(calificacionDTO);
        Long id = savedCalificacionDTO.getId();

        CalificacionDTO foundCalificacionDTO = calificacionService.findById(id);

        assertNotNull(foundCalificacionDTO);
        assertEquals(calificacionDTO.getValoracion(), foundCalificacionDTO.getValoracion());
        assertEquals(calificacionDTO.getComentario(), foundCalificacionDTO.getComentario());
        assertEquals(calificacionDTO.getFechaCalificacion(), foundCalificacionDTO.getFechaCalificacion());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testUpdateCalificacion() {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setValoracion(5);
        calificacionDTO.setComentario("Excelente servicio");
        calificacionDTO.setFechaCalificacion("2024-04-03");
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        CalificacionDTO savedCalificacionDTO = calificacionService.save(calificacionDTO);
        Long id = savedCalificacionDTO.getId();

        calificacionDTO.setComentario("Muy buen servicio");
        CalificacionDTO updatedCalificacionDTO = calificacionService.update(id, calificacionDTO);

        assertNotNull(updatedCalificacionDTO);
        assertEquals("Muy buen servicio", updatedCalificacionDTO.getComentario());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testDeleteCalificacion() {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setValoracion(5);
        calificacionDTO.setComentario("Excelente servicio");
        calificacionDTO.setFechaCalificacion("2024-04-03");
        // Suponiendo que existen los métodos para obtener los DTOs de usuario y propiedad

        CalificacionDTO savedCalificacionDTO = calificacionService.save(calificacionDTO);
        Long id = savedCalificacionDTO.getId();

        boolean isDeleted = calificacionService.delete(id);

        assertEquals(true, isDeleted);
    }
}
