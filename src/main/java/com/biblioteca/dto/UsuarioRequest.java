package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    
    // Nombre del usuario a crear
    private String nombre;
    
    // Correo del usuario a crear
    private String correo;
    
    // Tipo de usuario
    private String tipoUsuario;
}
