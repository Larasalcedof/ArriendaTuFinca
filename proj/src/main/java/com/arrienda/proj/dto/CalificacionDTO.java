package com.arrienda.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDTO {
    private Long id;
    private int valoracion;
    private String comentario;
    private String fechaCalificacion;
    private UsuarioDTO usuario;
    private PropiedadDTO propiedad;

    // Puedes agregar métodos de transformación si es necesario
    public static CalificacionDTO fromEntity(com.arrienda.proj.entity.Calificacion calificacion) {
        CalificacionDTO calificacionDTO = new CalificacionDTO();
        calificacionDTO.setId(calificacion.getId());
        calificacionDTO.setValoracion(calificacion.getValoracion());
        calificacionDTO.setComentario(calificacion.getComentario());
        calificacionDTO.setFechaCalificacion(calificacion.getFechaCalificacion());
        // Mapeo del usuario
        if (calificacion.getUsuario() != null) {
            calificacionDTO.setUsuario(UsuarioDTO.fromEntity(calificacion.getUsuario()));
        }
        // Mapeo de la propiedad
        if (calificacion.getPropiedad() != null) {
            calificacionDTO.setPropiedad(PropiedadDTO.fromEntity(calificacion.getPropiedad()));
        }
        return calificacionDTO;
    }

    // Método para convertir un CalificacionDTO a un Calificacion (opcional)
    public com.arrienda.proj.entity.Calificacion toEntity() {
        com.arrienda.proj.entity.Calificacion calificacion = new com.arrienda.proj.entity.Calificacion();
        calificacion.setId(this.getId());
        calificacion.setValoracion(this.getValoracion());
        calificacion.setComentario(this.getComentario());
        calificacion.setFechaCalificacion(this.getFechaCalificacion());
        // Mapeo del usuario
        if (this.getUsuario() != null) {
            calificacion.setUsuario(this.getUsuario().toEntity());
        }
        // Mapeo de la propiedad
        if (this.getPropiedad() != null) {
            calificacion.setPropiedad(this.getPropiedad().toEntity());
        }
        return calificacion;
    }
}
