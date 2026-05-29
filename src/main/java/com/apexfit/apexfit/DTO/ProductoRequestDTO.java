package com.apexfit.apexfit.DTO;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    
    @NotNull(message = "El nombre del producto es obligatorio")
    private String nombreProducto;
    
    @NotBlank(message = "El marca es obligatorio")
    private String marca;

    @NotNull(message = "El precio es obligatorio")
    private double precio;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
    
    
    @NotNull(message = "La fecha de llegada es obligatoria")
    @Past(message = "La fecha de llegada debe ser una fecha pasada")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaLlegada;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

}
