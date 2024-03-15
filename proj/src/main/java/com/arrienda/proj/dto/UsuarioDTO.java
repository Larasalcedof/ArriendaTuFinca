package com.arrienda.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private int rol;
    
    // Puedes agregar métodos de transformación si es necesario
    public static UsuarioDTO fromEntity(com.arrienda.proj.entity.Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioDTO.setRol(usuario.getRol());
        return usuarioDTO;
    }
    
    // Método para convertir un UsuarioDTO a un Usuario (opcional)
    public com.arrienda.proj.entity.Usuario toEntity() {
        com.arrienda.proj.entity.Usuario usuario = new com.arrienda.proj.entity.Usuario();
        usuario.setId(this.getId());
        usuario.setNombre(this.getNombre());
        usuario.setCorreoElectronico(this.getCorreoElectronico());
        usuario.setRol(this.getRol());
        return usuario;
    }
}
