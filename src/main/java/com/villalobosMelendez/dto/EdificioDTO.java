package com.villalobosMelendez.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EdificioDTO {
    private int idEdificio;

    @NotNull
    @Size(min = 1, max = 50)
    private String textura;

    @NotNull
    @Size(min = 1, max = 50)
    private String durabilidad;

    @NotNull
    @Size(min = 1, max = 50)
    private String marca;

    @NotNull
    private double volumen;
}
