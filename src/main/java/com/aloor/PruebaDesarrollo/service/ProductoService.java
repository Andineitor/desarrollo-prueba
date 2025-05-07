package com.aloor.PruebaDesarrollo.service;

import org.springframework.stereotype.Service;

import com.aloor.PruebaDesarrollo.entity.Producto;

@Service
public interface ProductoService {

	Producto crearProducto(Double precio, String categoria);
}
