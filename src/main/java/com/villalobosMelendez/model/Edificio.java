package com.villalobosMelendez.model;

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
public class Edificio {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEdificio;

    @Column(nullable = false, length = 70)
    private String textura;

    @Column(nullable = false, length = 70)
    private String durabilidad;

    @Column(nullable = false, length = 70)
    private String marca;

    private double volumen;

}
