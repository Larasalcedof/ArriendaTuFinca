package com.arrienda.proj.services;

import com.arrienda.proj.dto.PagoDTO;
import com.arrienda.proj.entity.Pago;
import com.arrienda.proj.repositories.PagoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PagoService(PagoRepository pagoRepository, ModelMapper modelMapper) {
        this.pagoRepository = pagoRepository;
        this.modelMapper = modelMapper;
    }

    public PagoDTO findById(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        return pagoOptional.map(pago -> modelMapper.map(pago, PagoDTO.class)).orElse(null);
    }

    public List<PagoDTO> findAll() {
        List<Pago> pagos = (List<Pago>) pagoRepository.findAll();
        return pagos.stream()
                .map(pago -> modelMapper.map(pago, PagoDTO.class))
                .collect(Collectors.toList());
    }

    public PagoDTO save(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        pago = pagoRepository.save(pago);
        return modelMapper.map(pago, PagoDTO.class);
    }

    public PagoDTO update(Long id, PagoDTO pagoDTO) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()) {
            Pago pago = pagoOptional.get();
            pago.setFechaPago(pagoDTO.getFechaPago());
            pago.setMonto(pagoDTO.getMonto());
            pago.setEstado(pagoDTO.getEstado());
            // Aquí puedes mapear otros campos si es necesario
            pago = pagoRepository.save(pago);
            return modelMapper.map(pago, PagoDTO.class);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()) {
            pagoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
