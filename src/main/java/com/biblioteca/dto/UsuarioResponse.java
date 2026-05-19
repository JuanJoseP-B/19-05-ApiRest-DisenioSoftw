package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    
    // ID generado por MongoDB
    private String id;
    
    // Nombre del usuario
    private String nombre;
    
    // Correo del usuario
    private String correo;
    
    // Tipo de usuario
    private String tipoUsuario;
}
