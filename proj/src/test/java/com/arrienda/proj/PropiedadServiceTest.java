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

import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.repositories.PropiedadRepository;
import com.arrienda.proj.services.PropiedadService;

@SpringBootTest
class PropiedadServiceTest {

    @Test
    void testFindById_Exists() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Propiedad propiedad = new Propiedad();
        PropiedadDTO expectedDTO = new PropiedadDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(propiedad));
        when(modelMapperMock.map(propiedad, PropiedadDTO.class)).thenReturn(expectedDTO);

        // Act
        PropiedadDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repositoryMock).findById(id);
        verify(modelMapperMock).map(propiedad, PropiedadDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        PropiedadDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testFindAll() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        List<Propiedad> propiedades = Arrays.asList(new Propiedad(), new Propiedad());
        List<PropiedadDTO> expectedDTOs = Arrays.asList(new PropiedadDTO(), new PropiedadDTO());
        when(repositoryMock.findAll()).thenReturn(propiedades);
        when(modelMapperMock.map(any(Propiedad.class), eq(PropiedadDTO.class))).thenReturn(new PropiedadDTO());

        // Act
        List<PropiedadDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(repositoryMock).findAll();
        verify(modelMapperMock, times(propiedades.size())).map(any(Propiedad.class), eq(PropiedadDTO.class));
    }

    @Test
    void testSave() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        Propiedad propiedad = new Propiedad();
        when(modelMapperMock.map(propiedadDTO, Propiedad.class)).thenReturn(propiedad);
        when(repositoryMock.save(propiedad)).thenReturn(propiedad);
        when(modelMapperMock.map(propiedad, PropiedadDTO.class)).thenReturn(propiedadDTO);

        // Act
        PropiedadDTO result = service.save(propiedadDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(propiedadDTO, Propiedad.class);
        verify(repositoryMock).save(propiedad);
        verify(modelMapperMock).map(propiedad, PropiedadDTO.class);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        Long id = 1L;
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        Propiedad propiedad = new Propiedad();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(propiedad));
        when(repositoryMock.save(propiedad)).thenReturn(propiedad);
        when(modelMapperMock.map(propiedad, PropiedadDTO.class)).thenReturn(propiedadDTO);

        // Act
        PropiedadDTO result = service.update(id, propiedadDTO);

        // Assert
        assertNotNull(result);
        verify(repositoryMock).findById(id);
        verify(repositoryMock).save(propiedad);
        verify(modelMapperMock).map(propiedad, PropiedadDTO.class);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        Long id = 1L;
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        PropiedadDTO result = service.update(id, propiedadDTO);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Propiedad propiedad = new Propiedad();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(propiedad));

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
        PropiedadRepository repositoryMock = mock(PropiedadRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PropiedadService service = new PropiedadService(repositoryMock, modelMapperMock);
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
