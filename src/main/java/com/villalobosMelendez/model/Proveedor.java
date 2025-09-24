package com.villalobosMelendez.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proveedor {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String ruc;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @OneToMany(mappedBy = "proveedor")
    @JsonManagedReference
    private List<Producto> productos;
}
