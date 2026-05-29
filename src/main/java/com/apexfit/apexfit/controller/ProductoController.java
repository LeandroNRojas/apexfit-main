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
import com.apexfit.apexfit.DTO.ProductoRequestDTO;
import com.apexfit.apexfit.DTO.ProductosResponseDTO;
import com.apexfit.apexfit.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // GET /api/productos → 200 OK con lista
    @GetMapping
    public ResponseEntity<List<ProductosResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(productoService.listar());
    }

    // GET /api/producto/{id} → 200 OK o 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<ProductosResponseDTO> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // POST /api/producto → 201 Created
    // @Valid dispara las validaciones del DTO.
    // Si un campo f!alla → GlobalExceptionHandler → 400
    //   { "nombre": "El nombre no puede estar vacío" }
    // Si el Id no existe → GlobalExceptionHandler → 400
    //   { "error": "Producto no encontrado con id: 99" }
    @PostMapping
    public ResponseEntity<ProductosResponseDTO> crear(
            @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.status(201).body(productoService.guardar(dto));
    }

    // PUT /api/producto/{id} → 200 OK o 404 Not Found
    // @Valid también aplica aquí: mismas validaciones que en POST.
    @PutMapping("/{id}")
    public ResponseEntity<ProductosResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        return productoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/producto/{id} → 204 No Content o 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── BÚSQUEDAS ────────────────────────────────────
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductosResponseDTO>> buscarNombre(
            @RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }
    //filtro por precio
    @GetMapping("/buscar/precio")
    public ResponseEntity<List<ProductosResponseDTO>> buscarPorPrecio(
            @RequestParam double max, @RequestParam double min) {
        return ResponseEntity.ok(productoService.buscarPorRangoDePrecio(min, max));
    }

}
