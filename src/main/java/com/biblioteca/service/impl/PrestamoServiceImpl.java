package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.model.Prestamo;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, EjemplarRepository ejemplarRepository) {
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public PrestamoResponse crearPrestamo(PrestamoRequest request) {
        // Verificar si el ejemplar existe
        Ejemplar ejemplar = ejemplarRepository.findById(request.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + request.getEjemplarId()));

        // Verificar que el ejemplar esté DISPONIBLE
        if (!"DISPONIBLE".equalsIgnoreCase(ejemplar.getEstado())) {
            throw new RuntimeException("El ejemplar no está disponible para préstamo");
        }

        // Crear el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioId(request.getUsuarioId());
        prestamo.setEjemplarId(request.getEjemplarId());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionEsperada(request.getFechaDevolucionEsperada());
        prestamo.setEstado("ACTIVO");

        Prestamo guardado = prestamoRepository.save(prestamo);

        // Actualizar el estado del ejemplar a PRESTADO
        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);

        return mapToResponse(guardado);
    }

    @Override
    public PrestamoResponse registrarDevolucion(String id) {
        // Buscar el préstamo
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con id: " + id));

        // Actualizar estado del préstamo
        prestamo.setEstado("DEVUELTO");
        Prestamo actualizado = prestamoRepository.save(prestamo);

        // Actualizar el estado del ejemplar a DISPONIBLE
        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        
        ejemplar.setEstado("DISPONIBLE");
        ejemplarRepository.save(ejemplar);

        return mapToResponse(actualizado);
    }

    @Override
    public PrestamoResponse consultarPrestamo(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con id: " + id));
        return mapToResponse(prestamo);
    }

    @Override
    public List<PrestamoResponse> listarPrestamos() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PrestamoResponse mapToResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getUsuarioId(),
                prestamo.getEjemplarId(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionEsperada(),
                prestamo.getEstado()
        );
    }
}
