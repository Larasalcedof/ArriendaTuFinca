package com.arrienda.proj.services;

import com.arrienda.proj.dto.CredencialesDTO;
import com.arrienda.proj.entity.Credenciales;
import com.arrienda.proj.repositories.CredencialesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredencialesService {

    private final CredencialesRepository credencialesRepository;
    private final ModelMapper modelMapper;

    public CredencialesService(CredencialesRepository credencialesRepository, ModelMapper modelMapper) {
        this.credencialesRepository = credencialesRepository;
        this.modelMapper = modelMapper;
    }

    public CredencialesDTO save(CredencialesDTO credencialesDTO) {
        Credenciales credenciales = modelMapper.map(credencialesDTO, Credenciales.class);
        credenciales = credencialesRepository.save(credenciales);
        return modelMapper.map(credenciales, CredencialesDTO.class);
    }

    public List<CredencialesDTO> findAll() {
        List<Credenciales> credencialesList = (List<Credenciales>) credencialesRepository.findAll();
        return credencialesList.stream()
                .map(credenciales -> modelMapper.map(credenciales, CredencialesDTO.class))
                .toList();
    }

    public CredencialesDTO findById(Long id) {
        Optional<Credenciales> credencialesOptional = credencialesRepository.findById(id);
        return credencialesOptional.map(credenciales -> modelMapper.map(credenciales, CredencialesDTO.class)).orElse(null);
    }

    public CredencialesDTO update(Long id, CredencialesDTO credencialesDTO) {
        Optional<Credenciales> credencialesOptional = credencialesRepository.findById(id);
        if (credencialesOptional.isPresent()) {
            Credenciales credenciales = credencialesOptional.get();
            credenciales.setCorreoElectronico(credencialesDTO.getCorreoElectronico());
            // Actualiza otras propiedades según sea necesario
            credenciales = credencialesRepository.save(credenciales);
            return modelMapper.map(credenciales, CredencialesDTO.class);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Credenciales> credencialesOptional = credencialesRepository.findById(id);
        if (credencialesOptional.isPresent()) {
            credencialesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
