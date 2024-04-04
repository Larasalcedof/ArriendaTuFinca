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
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.entity.Usuario;
import com.arrienda.proj.repositories.CredencialesRepository;
import com.arrienda.proj.repositories.UsuarioRepository;
import com.arrienda.proj.services.UsuarioService;

@SpringBootTest
class UsuarioServiceTest {

    @Test
    void testFindById_Exists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        Usuario usuario = new Usuario();
        UsuarioDTO expectedDTO = new UsuarioDTO();
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.of(usuario));
        when(modelMapperMock.map(usuario, UsuarioDTO.class)).thenReturn(expectedDTO);

        // Act
        UsuarioDTO result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(usuarioRepositoryMock).findById(id);
        verify(modelMapperMock).map(usuario, UsuarioDTO.class);
    }

    @Test
    void testFindById_NotExists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        UsuarioDTO result = service.findById(id);

        // Assert
        assertNull(result);
        verify(usuarioRepositoryMock).findById(id);
        verifyNoInteractions(modelMapperMock);
    }

    @Test
    void testFindAll() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        List<UsuarioDTO> expectedDTOs = Arrays.asList(new UsuarioDTO(), new UsuarioDTO());
        when(usuarioRepositoryMock.findAll()).thenReturn(usuarios);
        when(modelMapperMock.map(any(Usuario.class), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO());

        // Act
        List<UsuarioDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        verify(usuarioRepositoryMock).findAll();
        verify(modelMapperMock, times(usuarios.size())).map(any(Usuario.class), eq(UsuarioDTO.class));
    }

    @Test
    void testSave() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        Credenciales credenciales = new Credenciales();
        usuarioDTO.setCredenciales(credencialesDTO);
        when(modelMapperMock.map(usuarioDTO, Usuario.class)).thenReturn(usuario);
        when(modelMapperMock.map(credencialesDTO, Credenciales.class)).thenReturn(credenciales);
        when(credencialesRepositoryMock.save(credenciales)).thenReturn(credenciales);
        when(usuarioRepositoryMock.save(usuario)).thenReturn(usuario);
        when(modelMapperMock.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        // Act
        UsuarioDTO result = service.save(usuarioDTO);

        // Assert
        assertNotNull(result);
        verify(modelMapperMock).map(usuarioDTO, Usuario.class);
        verify(modelMapperMock).map(credencialesDTO, Credenciales.class);
        verify(credencialesRepositoryMock).save(credenciales);
        verify(usuarioRepositoryMock).save(usuario);
        verify(modelMapperMock).map(usuario, UsuarioDTO.class);
    }

    @Test
    void testUpdate_Exists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepositoryMock.save(usuario)).thenReturn(usuario);

        // Act
        UsuarioDTO result = service.update(id, usuarioDTO);

        // Assert
        assertNotNull(result);
        verify(usuarioRepositoryMock).findById(id);
        verify(usuarioRepositoryMock).save(usuario);
    }

    @Test
    void testUpdate_NotExists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        UsuarioDTO result = service.update(id, new UsuarioDTO());

        // Assert
        assertNull(result);
        verify(usuarioRepositoryMock).findById(id);
        verifyNoMoreInteractions(usuarioRepositoryMock);
    }

    @Test
    void testDelete_Exists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.of(usuario));

        // Act
        boolean result = service.delete(id);

        // Assert
        assertTrue(result);
        verify(usuarioRepositoryMock).findById(id);
        verify(usuarioRepositoryMock).deleteById(id);
    }

    @Test
    void testDelete_NotExists() {
        // Arrange
        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        CredencialesRepository credencialesRepositoryMock = mock(CredencialesRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);
        UsuarioService service = new UsuarioService(usuarioRepositoryMock, credencialesRepositoryMock, modelMapperMock);
        Long id = 1L;
        when(usuarioRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean result = service.delete(id);

        // Assert
        assertFalse(result);
        verify(usuarioRepositoryMock).findById(id);
        verifyNoMoreInteractions(usuarioRepositoryMock);
    }
}
