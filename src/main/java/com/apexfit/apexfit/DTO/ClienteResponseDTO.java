package com.apexfit.apexfit.DTO;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private int rut;
    private String apellido;
    private String nombre;
    private String correo;
    private String contraseña;
    private LocalDate fechaNacimiento;

}
