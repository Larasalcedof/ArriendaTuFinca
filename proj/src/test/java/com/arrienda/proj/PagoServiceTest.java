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

import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.entity.Pago;
import com.arrienda.proj.repositories.PagoRepository;
import com.arrienda.proj.services.PagoService;


@SpringBootTest
class PagoServiceTest {

    @Test
    void testFindById_Exists() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Pago pago = new Pago();
        PagoDTO expectedDTO = new PagoDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(pago));
        when(modelMapperMock.map(pago, PagoDTO.class)).thenReturn(expectedDTO);

        // Act
        PagoDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(repositoryMock).findById(id);
        verify(modelMapperMock).map(pago, PagoDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        Long id = 1L;
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        PagoDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testFindAll() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        List<Pago> pagos = Arrays.asList(new Pago(), new Pago());
        List<PagoDTO> expectedDTOs = Arrays.asList(new PagoDTO(), new PagoDTO());
        when(repositoryMock.findAll()).thenReturn(pagos);
        when(modelMapperMock.map(any(Pago.class), eq(PagoDTO.class))).thenReturn(new PagoDTO());

        // Act
        List<PagoDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(repositoryMock).findAll();
        verify(modelMapperMock, times(pagos.size())).map(any(Pago.class), eq(PagoDTO.class));
    }

    @Test
    void testSave() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        PagoDTO pagoDTO = new PagoDTO();
        Pago pago = new Pago();
        when(modelMapperMock.map(pagoDTO, Pago.class)).thenReturn(pago);
        when(repositoryMock.save(pago)).thenReturn(pago);
        when(modelMapperMock.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        // Act
        PagoDTO result = service.save(pagoDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(pagoDTO, Pago.class);
        verify(repositoryMock).save(pago);
        verify(modelMapperMock).map(pago, PagoDTO.class);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        Pago pago = new Pago();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(pago));
        when(repositoryMock.save(pago)).thenReturn(pago);
        when(modelMapperMock.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        // Act
        PagoDTO result = service.update(id, pagoDTO);

        // Assert
        assertNotNull(result);
        verify(repositoryMock).findById(id);
        verify(repositoryMock).save(pago);
        verify(modelMapperMock).map(pago, PagoDTO.class);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        when(repositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        PagoDTO result = service.update(id, pagoDTO);

        // Assert
        assertNull(result);
        verify(repositoryMock).findById(id);
        verifyNoMoreInteractions(repositoryMock);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
        Long id = 1L;
        Pago pago = new Pago();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(pago));

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
        PagoRepository repositoryMock = mock(PagoRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        PagoService service = new PagoService(repositoryMock, modelMapperMock);
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
