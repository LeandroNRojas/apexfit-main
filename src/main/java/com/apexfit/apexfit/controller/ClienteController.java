package com.apexfit.apexfit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apexfit.apexfit.DTO.ClienteRequestDTO;
import com.apexfit.apexfit.DTO.ClienteResponseDTO;
import com.apexfit.apexfit.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor

public class ClienteController {
    private final ClienteService clienteService;

    // GET /api/clientes → 200 OK con lista
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    // GET /api/clientes/{id} → 200 OK o 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/clientes → 201 Created
    // @Valid dispara las validaciones del DTO.
    // Si un campo falla → GlobalExceptionHandler → 400
    //   { "titulo": "El título no puede estar vacío" }
    // Si el categoriaId no existe → GlobalExceptionHandler → 400
    //   { "error": "Categoría no encontrada con id: 99" }
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(
            @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(201).body(clienteService.guardar(dto));
    }

    // PUT /api/clientes/{id} → 200 OK o 404 Not Found
    // @Valid también aplica aquí: mismas validaciones que en POST.
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/clientes/{id} → 204 No Content o 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── BÚSQUEDAS ────────────────────────────────────
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> buscarNombre(
            @RequestParam String nombre) {
        return ResponseEntity.ok(clienteService.buscarPorNombre(nombre));
    }

    
        
    
}
