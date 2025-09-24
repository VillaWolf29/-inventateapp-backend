package com.villalobosMelendez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 50)
    private String codigoBarra;

    private Double precioCompra;
    private Double precioVenta;
    private Integer stockActual;
    private Integer stockMinimo;

    @Column(length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @JsonBackReference
    private Proveedor proveedor;


}
