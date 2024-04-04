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

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.repositories.CredencialesRepository;
import com.arrienda.proj.services.CredencialesService;

@SpringBootTest
class CredencialesServiceTest {

    @Test
    void testSave() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        Credenciales credenciales = new Credenciales();
        when(modelMapperMock.map(credencialesDTO, Credenciales.class)).thenReturn(credenciales);
        when(repositoryMock.save(credenciales)).thenReturn(credenciales);
        when(modelMapperMock.map(credenciales, CredencialesDTO.class)).thenReturn(credencialesDTO);

        // Act
        CredencialesDTO result = service.save(credencialesDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(credencialesDTO, Credenciales.class);
        verify(repositoryMock).save(credenciales);
        verify(modelMapperMock).map(credenciales, CredencialesDTO.class);
    }

    @Test
    void testFindAll() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        List<Credenciales> credencialesList = Arrays.asList(new Credenciales(), new Credenciales());
        List<CredencialesDTO> expectedDTOs = Arrays.asList(new CredencialesDTO(), new CredencialesDTO());
        when(repositoryMock.findAll()).thenReturn(credencialesList);
        when(modelMapperMock.map(any(Credenciales.class), eq(CredencialesDTO.class))).thenReturn(new CredencialesDTO());

        // Act
        List<CredencialesDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(repositoryMock).findAll();
        verify(modelMapperMock, times(credencialesList.size())).map(any(Credenciales.class), eq(CredencialesDTO.class));
    }

    @Test
    void testFindById_Exists() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Credenciales credenciales = new Credenciales();
        CredencialesDTO expectedDTO = new CredencialesDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(credenciales));
        when(modelMapperMock.map(credenciales, CredencialesDTO.class)).thenReturn(expectedDTO);

        // Act
        CredencialesDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repositoryMock).findById(id);
        verify(modelMapperMock).map(credenciales, CredencialesDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        CredencialesDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        Long id = 1L;
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        Credenciales credenciales = new Credenciales();
        credenciales.setCorreoElectronico("test@test.com");
        when(repositoryMock.findById(id)).thenReturn(Optional.of(credenciales));
        when(repositoryMock.save(credenciales)).thenReturn(credenciales);
        when(modelMapperMock.map(credenciales, CredencialesDTO.class)).thenReturn(credencialesDTO);

        // Act
        CredencialesDTO result = service.update(id, credencialesDTO);

        // Assert
        assertNotNull(result);
        assertEquals(credencialesDTO.getCorreoElectronico(), credenciales.getCorreoElectronico());
        verify(repositoryMock).findById(id);
        verify(repositoryMock).save(credenciales);
        verify(modelMapperMock).map(credenciales, CredencialesDTO.class);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        Long id = 1L;
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        CredencialesDTO result = service.update(id, credencialesDTO);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Credenciales credenciales = new Credenciales();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(credenciales));

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
        CredencialesRepository repositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        CredencialesService service = new CredencialesService(repositoryMock, modelMapperMock);
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

