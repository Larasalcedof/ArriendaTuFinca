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

import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.entity.Solicitud;
import com.arrienda.proj.repositories.SolicitudRepository;
import com.arrienda.proj.services.SolicitudService;

@SpringBootTest
class SolicitudServiceTest {

    @Test
    void testFindById_Exists() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Solicitud solicitud = new Solicitud();
        SolicitudDTO expectedDTO = new SolicitudDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(solicitud));
        when(modelMapperMock.map(solicitud, SolicitudDTO.class)).thenReturn(expectedDTO);

        // Act
        SolicitudDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repositoryMock).findById(id);
        verify(modelMapperMock).map(solicitud, SolicitudDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        SolicitudDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testFindAll() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        List<Solicitud> solicitudes = Arrays.asList(new Solicitud(), new Solicitud());
        List<SolicitudDTO> expectedDTOs = Arrays.asList(new SolicitudDTO(), new SolicitudDTO());
        when(repositoryMock.findAll()).thenReturn(solicitudes);
        when(modelMapperMock.map(any(Solicitud.class), eq(SolicitudDTO.class))).thenReturn(new SolicitudDTO());

        // Act
        List<SolicitudDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(repositoryMock).findAll();
        verify(modelMapperMock, times(solicitudes.size())).map(any(Solicitud.class), eq(SolicitudDTO.class));
    }

    @Test
    void testSave() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        Solicitud solicitud = new Solicitud();
        when(modelMapperMock.map(solicitudDTO, Solicitud.class)).thenReturn(solicitud);
        when(repositoryMock.save(solicitud)).thenReturn(solicitud);
        when(modelMapperMock.map(solicitud, SolicitudDTO.class)).thenReturn(solicitudDTO);

        // Act
        SolicitudDTO result = service.save(solicitudDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(solicitudDTO, Solicitud.class);
        verify(repositoryMock).save(solicitud);
        verify(modelMapperMock).map(solicitud, SolicitudDTO.class);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        Long id = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        Solicitud solicitud = new Solicitud();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(solicitud));
        when(repositoryMock.save(solicitud)).thenReturn(solicitud);
        when(modelMapperMock.map(solicitud, SolicitudDTO.class)).thenReturn(solicitudDTO);

        // Act
        SolicitudDTO result = service.update(id, solicitudDTO);

        // Assert
        assertNotNull(result);
        verify(repositoryMock).findById(id);
        verify(repositoryMock).save(solicitud);
        verify(modelMapperMock).map(solicitud, SolicitudDTO.class);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        Long id = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        SolicitudDTO result = service.update(id, solicitudDTO);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Solicitud solicitud = new Solicitud();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(solicitud));

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
        SolicitudRepository repositoryMock = mock(SolicitudRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        SolicitudService service = new SolicitudService(repositoryMock, modelMapperMock);
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
