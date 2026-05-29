package com.apexfit.apexfit.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.apexfit.apexfit.DTO.ProductoRequestDTO;
import com.apexfit.apexfit.DTO.ProductosResponseDTO;
import com.apexfit.apexfit.model.Producto;
import com.apexfit.apexfit.model.TipoProducto;
import com.apexfit.apexfit.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    // ── MAPEO PRIVADO: Entidad → ResponseDTO ─────────
    private ProductosResponseDTO mapToDTO(Producto producto) {
        return new ProductosResponseDTO(
                
                producto.getNombreProducto(),
                producto.getMarca(),
                producto.getPrecio(),
                producto.getTipo(),
                producto.getFechaLlegada(),
                producto.getStock()
        );
    }
    
    // ── LISTAR TODOS ─────────────────────────────────
    public List<ProductosResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ── OBTENER POR ID ───────────────────────────────
    public Optional<ProductosResponseDTO> obtenerPorId(Long id) {
        return repository.findById(id).map(this::mapToDTO);
    }

    // ── GUARDAR ──────────────────────────────────────
    public ProductosResponseDTO guardar(ProductoRequestDTO dto) {
        Producto producto = new Producto(
                0L,
                dto.getNombreProducto(),
                dto.getMarca(),
                dto.getPrecio(),
                TipoProducto.valueOf(dto.getTipo().toUpperCase()),
                dto.getFechaLlegada(),
                dto.getStock()
        );
        return mapToDTO(repository.save(producto));
    }

    // ── ACTUALIZAR ───────────────────────────────────
    public Optional<ProductosResponseDTO> actualizar(Long id, ProductoRequestDTO dto) {
        return repository.findById(id).map(existente -> {
            existente.setNombreProducto(dto.getNombreProducto());
            existente.setMarca(dto.getMarca());
            existente.setPrecio(dto.getPrecio());
            existente.setTipo(TipoProducto.valueOf(dto.getTipo().toUpperCase()));
            existente.setFechaLlegada(dto.getFechaLlegada());
            existente.setStock(dto.getStock());
            return mapToDTO(repository.save(existente));
        });
    }


    // ── ELIMINAR ─────────────────────────────────────
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // ── BÚSQUEDAS (de Clase 2, adaptadas a ResponseDTO) ──
    public List<ProductosResponseDTO> buscarPorNombre(String texto) {
        return repository.findByNombreProductoContainingIgnoreCase(texto)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    
    //BUSQUEDA ENTRE PRECIO MX Y MN
    public List<ProductosResponseDTO> buscarPorRangoDePrecio(Double min, Double max) {
        return repository.findByPrecioBetween(min, max)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //BUSQUEDA POR TIPO DE PRODUCTO
    public List<ProductosResponseDTO> buscarPorTipo(String tipo) {
        return repository.findByTipo(TipoProducto.valueOf(tipo.toUpperCase()))
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}

    
