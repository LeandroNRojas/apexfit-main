package com.apexfit.apexfit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table (name= "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=true, length = 20, nullable = false)
    private int rut;

    @Column(unique=true, length = 50, nullable = false)
    private String correo;

    @Column(nullable = false, length = 60)
    private String contraseña;
    
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = true)
    private LocalDate fechaNacimiento;

  

}
