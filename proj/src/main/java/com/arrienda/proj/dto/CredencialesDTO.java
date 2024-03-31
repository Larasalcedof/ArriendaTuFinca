package com.arrienda.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredencialesDTO {
    private Long id;
    private String correoElectronico;
    private String contrasena;
    
    // Puedes agregar métodos de transformación si es necesario
    public static CredencialesDTO fromEntity(com.arrienda.proj.entity.Credenciales credenciales) {
        CredencialesDTO credencialesDTO = new CredencialesDTO();
        credencialesDTO.setId(credenciales.getId());
        credencialesDTO.setCorreoElectronico(credenciales.getCorreoElectronico());
        // No incluyo la contraseña para no exponerla en el DTO
        return credencialesDTO;
    }
    
    // Método para convertir un CredencialesDTO a un Credenciales (opcional)
    public com.arrienda.proj.entity.Credenciales toEntity() {
        com.arrienda.proj.entity.Credenciales credenciales = new com.arrienda.proj.entity.Credenciales();
        credenciales.setId(this.getId());
        credenciales.setCorreoElectronico(this.getCorreoElectronico());
        // No incluyo la contraseña para no exponerla en la entidad
        return credenciales;
    }
}
