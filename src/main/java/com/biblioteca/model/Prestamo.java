package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prestamos")
public class Prestamo {
    @Id
    private String id;
    
    // ID del usuario que solicita el préstamo
    private String usuarioId;
    
    // ID del ejemplar prestado
    private String ejemplarId;
    
    // Fecha en que se realiza el préstamo
    private LocalDate fechaPrestamo;
    
    // Fecha acordada de devolución
    private LocalDate fechaDevolucionEsperada;
    
    // Estado del préstamo (Ej: ACTIVO, DEVUELTO)
    private String estado;
}
