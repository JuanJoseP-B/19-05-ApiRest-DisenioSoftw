package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    
    // Nombre del usuario
    private String nombre;
    
    // Correo electrónico del usuario
    private String correo;
    
    // Tipo de usuario (Ej: ESTUDIANTE, PROFESOR, BIBLIOTECARIO)
    private String tipoUsuario;
}
