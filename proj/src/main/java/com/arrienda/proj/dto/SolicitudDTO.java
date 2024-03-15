package com.arrienda.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    private Long id;
    private String fechaSolicitud;
    private int estado;
    private UsuarioDTO arrendatario;
    private PropiedadDTO propiedad;

    // Puedes agregar métodos de transformación si es necesario
    public static SolicitudDTO fromEntity(com.arrienda.proj.entity.Solicitud solicitud) {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setId(solicitud.getId());
        solicitudDTO.setFechaSolicitud(solicitud.getFechaSolicitud());
        solicitudDTO.setEstado(solicitud.getEstado());
        // Mapeo del arrendatario
        if (solicitud.getArrendatario() != null) {
            solicitudDTO.setArrendatario(UsuarioDTO.fromEntity(solicitud.getArrendatario()));
        }
        // Mapeo de la propiedad
        if (solicitud.getPropiedad() != null) {
            solicitudDTO.setPropiedad(PropiedadDTO.fromEntity(solicitud.getPropiedad()));
        }
        return solicitudDTO;
    }

    // Método para convertir un SolicitudDTO a un Solicitud (opcional)
    public com.arrienda.proj.entity.Solicitud toEntity() {
        com.arrienda.proj.entity.Solicitud solicitud = new com.arrienda.proj.entity.Solicitud();
        solicitud.setId(this.getId());
        solicitud.setFechaSolicitud(this.getFechaSolicitud());
        solicitud.setEstado(this.getEstado());
        // Mapeo del arrendatario
        if (this.getArrendatario() != null) {
            solicitud.setArrendatario(this.getArrendatario().toEntity());
        }
        // Mapeo de la propiedad
        if (this.getPropiedad() != null) {
            solicitud.setPropiedad(this.getPropiedad().toEntity());
        }
        return solicitud;
    }
}
