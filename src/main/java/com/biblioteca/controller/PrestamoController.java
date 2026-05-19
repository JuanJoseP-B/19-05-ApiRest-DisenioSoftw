package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearPrestamo(@RequestBody PrestamoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.crearPrestamo(request));
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoResponse> registrarDevolucion(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> consultarPrestamo(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.consultarPrestamo(id));
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.listarPrestamos());
    }
}
