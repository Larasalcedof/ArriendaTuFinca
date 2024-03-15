package com.arrienda.proj.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private String fechaPago;
    private BigDecimal monto;
    private int estado;
    private UsuarioDTO usuario;
    private PropiedadDTO propiedad;

    // Puedes agregar métodos de transformación si es necesario
    public static PagoDTO fromEntity(com.arrienda.proj.entity.Pago pago) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId(pago.getId());
        pagoDTO.setFechaPago(pago.getFechaPago());
        pagoDTO.setMonto(pago.getMonto());
        pagoDTO.setEstado(pago.getEstado());
        // Mapeo del usuario
        if (pago.getUsuario() != null) {
            pagoDTO.setUsuario(UsuarioDTO.fromEntity(pago.getUsuario()));
        }
        // Mapeo de la propiedad
        if (pago.getPropiedad() != null) {
            pagoDTO.setPropiedad(PropiedadDTO.fromEntity(pago.getPropiedad()));
        }
        return pagoDTO;
    }

    // Método para convertir un PagoDTO a un Pago (opcional)
    public com.arrienda.proj.entity.Pago toEntity() {
        com.arrienda.proj.entity.Pago pago = new com.arrienda.proj.entity.Pago();
        pago.setId(this.getId());
        pago.setFechaPago(this.getFechaPago());
        pago.setMonto(this.getMonto());
        pago.setEstado(this.getEstado());
        // Mapeo del usuario
        if (this.getUsuario() != null) {
            pago.setUsuario(this.getUsuario().toEntity());
        }
        // Mapeo de la propiedad
        if (this.getPropiedad() != null) {
            pago.setPropiedad(this.getPropiedad().toEntity());
        }
        return pago;
    }
}
