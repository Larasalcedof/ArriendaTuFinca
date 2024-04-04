package com.arrienda.proj.services;

import com.arrienda.proj.dto.PropiedadDTO;
import com.arrienda.proj.entity.Propiedad;
import com.arrienda.proj.repositories.PropiedadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final ModelMapper modelMapper;

    public PropiedadService(PropiedadRepository propiedadRepository, ModelMapper modelMapper) {
        this.propiedadRepository = propiedadRepository;
        this.modelMapper = modelMapper;
    }

    public PropiedadDTO findById(Long id) {
        Optional<Propiedad> propiedadOptional = propiedadRepository.findById(id);
        return propiedadOptional.map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class)).orElse(null);
    }

    public List<PropiedadDTO> findAll() {
        List<Propiedad> propiedades = (List<Propiedad>) propiedadRepository.findAll();
        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }

    public PropiedadDTO save(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepository.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    public PropiedadDTO update(Long id, PropiedadDTO propiedadDTO) {
        Optional<Propiedad> propiedadOptional = propiedadRepository.findById(id);
        if (propiedadOptional.isPresent()) {
            Propiedad propiedad = propiedadOptional.get();
            propiedad.setNombre(propiedadDTO.getNombre());
            propiedad.setDescripcion(propiedadDTO.getDescripcion());
            propiedad.setUbicacion(propiedadDTO.getUbicacion());
            propiedad.setCostoArrendamiento(propiedadDTO.getCostoArrendamiento());
            propiedad.setDisponible(propiedadDTO.isDisponible());
            propiedad = propiedadRepository.save(propiedad);
            return modelMapper.map(propiedad, PropiedadDTO.class);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Propiedad> propiedadOptional = propiedadRepository.findById(id);
        if (propiedadOptional.isPresent()) {
            propiedadRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
