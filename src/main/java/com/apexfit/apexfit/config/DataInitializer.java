package com.apexfit.apexfit.config;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apexfit.apexfit.model.Cliente;
import com.apexfit.apexfit.model.Producto;
import com.apexfit.apexfit.model.TipoProducto;
import com.apexfit.apexfit.repository.ClienteRepository;
import com.apexfit.apexfit.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    // CommandLineRunner obliga a implementar run().
    // Spring lo ejecuta automáticamente después de que el contexto y la BD están listos.

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public void run(String... args) {

        // ── GUARDIA PRINCIPAL ────────────────────────
        // Si ya existen productos en la BD, no hacemos nada.
        if (productoRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }

        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");

        // ── clientes ───────────────────────────────
        clienteRepository.save(
                new Cliente(null, 184333082, "cr.sotoe@duocuc.cl", "12345678", "cristian", "soto", LocalDate.of(1993, 4, 10)));
        clienteRepository.save(
                new Cliente(null, 184333083, "juan.perez@duocuc.cl", "87654321", "juan", "perez", LocalDate.of(1990, 7, 15)));
        clienteRepository.save(
                new Cliente(null, 184333084, "maria.gomez@duocuc.cl", "11111111", "maria", "gomez", LocalDate.of(1985, 12, 20)));

        // ── productos ───────────────────────────────────
        productoRepository.save(
            new Producto(0L, "PROTEINA+", "marca1", 10000, TipoProducto.SUPLEMENTO, LocalDate.of(2024, 1, 1),10));
        productoRepository.save(
            new Producto(0L, "PROTEINA", "marca2", 15000, TipoProducto.SUPLEMENTO, LocalDate.of(2024, 1, 1),10));
        productoRepository.save(
            new Producto(0L, "PESAS", "marca3",5000, TipoProducto.EQUIPO, LocalDate.of(2024, 1, 1),10));
        productoRepository.save(
            new Producto(0L, "CAMISETA", "marca4", 30000, TipoProducto.ROPA, LocalDate.of(2024, 1, 1),10));
        
        log.info(">>> DataInitializer: {} clientes y {} productos insertados correctamente.",
                clienteRepository.count(), productoRepository.count());
    }
}
