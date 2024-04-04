package com.arrienda.proj;

import com.arrienda.proj.services.CalificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class CalificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
     CalificacionService calificacionService;

    @Test
     void testGetAllCalificaciones() throws Exception {
        mockMvc.perform(get("/calificaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testGetCalificacionById() throws Exception {
        mockMvc.perform(get("/calificaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testCreateCalificacion() throws Exception {
        mockMvc.perform(post("/calificaciones")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testUpdateCalificacion() throws Exception {
        mockMvc.perform(put("/calificaciones/1")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
     void testDeleteCalificacion() throws Exception {
        mockMvc.perform(delete("/calificaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
