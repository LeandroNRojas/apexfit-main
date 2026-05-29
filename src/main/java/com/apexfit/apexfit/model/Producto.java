package com.apexfit.apexfit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Entity
@Table(name= "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipo;

    @Column(nullable = false)
    private LocalDate fechaLlegada;

    @Column(nullable= false)
    private int stock;

}
