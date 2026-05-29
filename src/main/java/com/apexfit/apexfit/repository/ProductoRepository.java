package com.apexfit.apexfit.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apexfit.apexfit.model.Producto;
import com.apexfit.apexfit.model.TipoProducto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // → SELECT * FROM productos WHERE UPPER(nombre_producto) LIKE UPPER('%?%')
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);

    
    // JPQL con ORDER BY
    @Query("SELECT l FROM Producto l WHERE l.precio <= :precioMax ORDER BY l.precio DESC")
    List<Producto> findProductosBajoPresupuesto(@Param("precioMax") double precioMax);

    // → SELECT * FROM productos WHERE precio BETWEEN ? AND ?
    List<Producto> findByPrecioBetween(double min, double max);

    @Query("SELECT l FROM Producto l WHERE l.tipo = :tipo")
    List<Producto> findByTipo(@Param("tipo") TipoProducto tipo);
}
