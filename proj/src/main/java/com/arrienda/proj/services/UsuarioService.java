package com.arrienda.proj.services;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.dto.UsuarioDTO;
import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.entity.Usuario;
import com.arrienda.proj.repositories.CredencialesRepository;
import com.arrienda.proj.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CredencialesRepository credencialesRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, CredencialesRepository credencialesRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.credencialesRepository = credencialesRepository;
        this.modelMapper = modelMapper;
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)).orElse(null);
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        
        // Extraer las credenciales del DTO de usuario
        CredencialesDTO credencialesDTO = usuarioDTO.getCredenciales();
        
        // Crear una instancia de Credenciales a partir del DTO recibido
        Credenciales credenciales = modelMapper.map(credencialesDTO, Credenciales.class);

        // Guardar las credenciales y obtener el objeto persistido
        credenciales = credencialesRepository.save(credenciales);
        
        // Asignar las credenciales al usuario
        usuario.setCredenciales(credenciales);
        
        // Guardar el usuario
        usuario = usuarioRepository.save(usuario);
        
        // Mapear el usuario guardado de nuevo al DTO y retornarlo
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setRol(usuarioDTO.getRol());
            usuarioRepository.save(usuario);
            return usuarioDTO;
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
