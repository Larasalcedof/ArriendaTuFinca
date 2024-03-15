package com.arrienda.proj.services;

import com.arrienda.proj.dto.CalificacionDTO;
import com.arrienda.proj.entity.Calificacion;
import com.arrienda.proj.repositories.CalificacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CalificacionService(CalificacionRepository calificacionRepository, ModelMapper modelMapper) {
        this.calificacionRepository = calificacionRepository;
        this.modelMapper = modelMapper;
    }

    public CalificacionDTO findById(Long id) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(id);
        return calificacionOptional.map(calificacion -> modelMapper.map(calificacion, CalificacionDTO.class)).orElse(null);
    }

    public List<CalificacionDTO> findAll() {
        List<Calificacion> calificaciones = (List<Calificacion>) calificacionRepository.findAll();
        return calificaciones.stream()
                .map(calificacion -> modelMapper.map(calificacion, CalificacionDTO.class))
                .collect(Collectors.toList());
    }

    public CalificacionDTO save(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = modelMapper.map(calificacionDTO, Calificacion.class);
        calificacion = calificacionRepository.save(calificacion);
        return modelMapper.map(calificacion, CalificacionDTO.class);
    }

    public CalificacionDTO update(Long id, CalificacionDTO calificacionDTO) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(id);
        if (calificacionOptional.isPresent()) {
            Calificacion calificacion = calificacionOptional.get();
            calificacion.setValoracion(calificacionDTO.getValoracion());
            calificacion.setComentario(calificacionDTO.getComentario());
            // Aquí puedes mapear otros campos si es necesario
            calificacion = calificacionRepository.save(calificacion);
            return modelMapper.map(calificacion, CalificacionDTO.class);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(id);
        if (calificacionOptional.isPresent()) {
            calificacionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
