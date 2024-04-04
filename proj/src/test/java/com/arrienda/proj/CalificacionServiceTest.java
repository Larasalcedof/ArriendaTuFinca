package com.arrienda.proj;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.arrienda.proj.dto.CalificacionDTO;
import com.arrienda.proj.entity.Calificacion;
import com.arrienda.proj.repositories.CalificacionRepository;
import com.arrienda.proj.services.CalificacionService;

@SpringBootTest
class CalificacionServiceTest {

    @Test
    void testFindById_Exists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Calificacion calificacion = new Calificacion();
        CalificacionDTO expectedDTO = new CalificacionDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(calificacion));
        when(modelMapperMock.map(calificacion, CalificacionDTO.class)).thenReturn(expectedDTO);

        // Act
        CalificacionDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repositoryMock).findById(id);
        verify(modelMapperMock).map(calificacion, CalificacionDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        CalificacionDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testFindAll() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        List<Calificacion> calificaciones = Arrays.asList(new Calificacion(), new Calificacion());
        List<CalificacionDTO> expectedDTOs = Arrays.asList(new CalificacionDTO(), new CalificacionDTO());
        when(repositoryMock.findAll()).thenReturn(calificaciones);
        when(modelMapperMock.map(any(Calificacion.class), eq(CalificacionDTO.class))).thenReturn(new CalificacionDTO());

        // Act
        List<CalificacionDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(repositoryMock).findAll();
        verify(modelMapperMock, times(calificaciones.size())).map(any(Calificacion.class), eq(CalificacionDTO.class));
    }

    @Test
    void testSave() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        Calificacion calificacion = new Calificacion();
        when(modelMapperMock.map(calificacionDTO, Calificacion.class)).thenReturn(calificacion);
        when(repositoryMock.save(calificacion)).thenReturn(calificacion);
        when(modelMapperMock.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        // Act
        CalificacionDTO result = service.save(calificacionDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(calificacionDTO, Calificacion.class);
        verify(repositoryMock).save(calificacion);
        verify(modelMapperMock).map(calificacion, CalificacionDTO.class);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        Calificacion calificacion = new Calificacion();
        calificacion.setValoracion(5);
        when(repositoryMock.findById(id)).thenReturn(Optional.of(calificacion));
        when(repositoryMock.save(calificacion)).thenReturn(calificacion);
        when(modelMapperMock.map(calificacion, CalificacionDTO.class)).thenReturn(calificacionDTO);

        // Act
        CalificacionDTO result = service.update(id, calificacionDTO);

        // Assert
        assertNotNull(result);
        assertEquals(calificacionDTO, result);
        assertEquals(calificacionDTO.getValoracion(), calificacion.getValoracion());
        verify(repositoryMock).findById(id);
        verify(repositoryMock).save(calificacion);
        verify(modelMapperMock).map(calificacion, CalificacionDTO.class);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        CalificacionDTO result = service.update(id, calificacionDTO);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Calificacion calificacion = new Calificacion();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(calificacion));

        // Act
        boolean result = service.delete(id);

        // Assert
        assertTrue(result);
        verify(repositoryMock).findById(id);
        verify(repositoryMock).deleteById(id);
    }

    @Test
    void testDelete_NotExists() {
        // Arrange
        CalificacionRepository repositoryMock = mock(CalificacionRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CalificacionService service = new CalificacionService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean result = service.delete(id);

        // Assert
        assertFalse(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
    }
}

