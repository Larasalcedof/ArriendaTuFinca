package com.arrienda.proj.services;

import com.arrienda.proj.dto.SolicitudDTO;
import com.arrienda.proj.entity.Solicitud;
import com.arrienda.proj.repositories.SolicitudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ModelMapper modelMapper;

    public SolicitudService(SolicitudRepository solicitudRepository, ModelMapper modelMapper) {
        this.solicitudRepository = solicitudRepository;
        this.modelMapper = modelMapper;
    }

    public SolicitudDTO findById(Long id) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        return solicitudOptional.map(solicitud -> modelMapper.map(solicitud, SolicitudDTO.class)).orElse(null);
    }

    public List<SolicitudDTO> findAll() {
        List<Solicitud> solicitudes = (List<Solicitud>) solicitudRepository.findAll();
        return solicitudes.stream()
                .map(solicitud -> modelMapper.map(solicitud, SolicitudDTO.class))
                .collect(Collectors.toList());
    }

    public SolicitudDTO save(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepository.save(solicitud);
        return modelMapper.map(solicitud, SolicitudDTO.class);
    }

    public SolicitudDTO update(Long id, SolicitudDTO solicitudDTO) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        if (solicitudOptional.isPresent()) {
            Solicitud solicitud = solicitudOptional.get();
            solicitud.setFechaSolicitud(solicitudDTO.getFechaSolicitud());
            solicitud.setEstado(solicitudDTO.getEstado());
            // Aquí puedes mapear otros campos si es necesario
            solicitud = solicitudRepository.save(solicitud);
            return modelMapper.map(solicitud, SolicitudDTO.class);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        if (solicitudOptional.isPresent()) {
            solicitudRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
