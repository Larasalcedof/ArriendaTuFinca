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
public class PropiedadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private BigDecimal costoArrendamiento;
    private boolean disponible;
    private UsuarioDTO arrendador;

    // Puedes agregar métodos de transformación si es necesario
    public static PropiedadDTO fromEntity(com.arrienda.proj.entity.Propiedad propiedad) {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setId(propiedad.getId());
        propiedadDTO.setNombre(propiedad.getNombre());
        propiedadDTO.setDescripcion(propiedad.getDescripcion());
        propiedadDTO.setUbicacion(propiedad.getUbicacion());
        propiedadDTO.setCostoArrendamiento(propiedad.getCostoArrendamiento());
        propiedadDTO.setDisponible(propiedad.isDisponible());
        // Mapeo del arrendador
        if (propiedad.getArrendador() != null) {
            propiedadDTO.setArrendador(UsuarioDTO.fromEntity(propiedad.getArrendador()));
        }
        return propiedadDTO;
    }

    // Método para convertir un PropiedadDTO a un Propiedad (opcional)
    public com.arrienda.proj.entity.Propiedad toEntity() {
        com.arrienda.proj.entity.Propiedad propiedad = new com.arrienda.proj.entity.Propiedad();
        propiedad.setId(this.getId());
        propiedad.setNombre(this.getNombre());
        propiedad.setDescripcion(this.getDescripcion());
        propiedad.setUbicacion(this.getUbicacion());
        propiedad.setCostoArrendamiento(this.getCostoArrendamiento());
        propiedad.setDisponible(this.isDisponible());
        // Mapeo del arrendador
        if (this.getArrendador() != null) {
            propiedad.setArrendador(this.getArrendador().toEntity());
        }
        return propiedad;
    }
}
