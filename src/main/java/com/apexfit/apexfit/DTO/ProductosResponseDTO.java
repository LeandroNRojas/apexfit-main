package com.apexfit.apexfit.DTO;
import java.time.LocalDate;
import com.apexfit.apexfit.model.TipoProducto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosResponseDTO {
    private String nombreProducto;
    private String marca;
    private double precio;
    private TipoProducto tipo;
    private LocalDate fechaLlegada;
    private int stock;


}
