package com.villalobosMelendez.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorDTO {
    private int idProveedor;

    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50)
    private String ruc;

    @NotNull
    @Size(min = 1, max = 50)
    private String direccion;

    @NotNull
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @Email
    private String correo;
}
