package com.aloor.PruebaDesarrollo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Factura {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double precioConDescuento;
    private double costoEnvio;
    private double precioFinal;

    @OneToOne
    private Producto producto;

}
