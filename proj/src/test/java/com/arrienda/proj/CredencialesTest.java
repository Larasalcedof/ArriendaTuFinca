package com.arrienda.proj;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.services.CredencialesService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CredencialesTest {

    @Autowired
    private CredencialesService credencialesService;

    @Test
    public void testSaveCredenciales() {
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("test@example.com");
        credencialesDTO.setContrasena("password");

        CredencialesDTO savedCredencialesDTO = credencialesService.save(credencialesDTO);

        assertNotNull(savedCredencialesDTO.getId());
        assertEquals(credencialesDTO.getCorreoElectronico(), savedCredencialesDTO.getCorreoElectronico());
        // Asegúrate de no exponer la contraseña en el DTO
    }

    @Test
    public void testFindAllCredenciales() {
        List<CredencialesDTO> credencialesDTOList = credencialesService.findAll();

        // Asegúrate de que la lista no esté vacía
        assertNotNull(credencialesDTOList);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testFindByIdCredenciales() {
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("test@example.com");
        credencialesDTO.setContrasena("password");

        CredencialesDTO savedCredencialesDTO = credencialesService.save(credencialesDTO);
        Long id = savedCredencialesDTO.getId();

        CredencialesDTO foundCredencialesDTO = credencialesService.findById(id);

        assertNotNull(foundCredencialesDTO);
        assertEquals(credencialesDTO.getCorreoElectronico(), foundCredencialesDTO.getCorreoElectronico());
        // Asegúrate de no exponer la contraseña en el DTO
    }

    @Test
    public void testUpdateCredenciales() {
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("test@example.com");
        credencialesDTO.setContrasena("password");

        CredencialesDTO savedCredencialesDTO = credencialesService.save(credencialesDTO);
        Long id = savedCredencialesDTO.getId();

        credencialesDTO.setCorreoElectronico("updated@example.com");
        CredencialesDTO updatedCredencialesDTO = credencialesService.update(id, credencialesDTO);

        assertNotNull(updatedCredencialesDTO);
        assertEquals("updated@example.com", updatedCredencialesDTO.getCorreoElectronico());
        // Asegúrate de no exponer la contraseña en el DTO
    }

    @Test
    public void testDeleteCredenciales() {
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setCorreoElectronico("test@example.com");
        credencialesDTO.setContrasena("password");

        CredencialesDTO savedCredencialesDTO = credencialesService.save(credencialesDTO);
        Long id = savedCredencialesDTO.getId();

        boolean isDeleted = credencialesService.delete(id);

        assertEquals(true, isDeleted);
    }
}
