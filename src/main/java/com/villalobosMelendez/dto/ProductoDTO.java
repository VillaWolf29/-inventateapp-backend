package com.villalobosMelendez.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {
    private int idProducto;
    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 100)
    private String descripcion;

    @NotNull
    private String codigoBarra;

    @NotNull
    private Double precioCompra;

    @NotNull
    private Double precioVenta;

    @NotNull
    private Integer stockActual;

    @NotNull
    private Integer stockMinimo;

    @NotNull
    private String estado;

    private ProveedorDTO proveedor;
}
