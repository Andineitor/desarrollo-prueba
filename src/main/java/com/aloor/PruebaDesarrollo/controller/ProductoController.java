package com.aloor.PruebaDesarrollo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aloor.PruebaDesarrollo.entity.Producto;
import com.aloor.PruebaDesarrollo.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crear")	
    public Producto crearProducto(@RequestParam double precio, @RequestParam String categoria) {
        return productoService.crearProducto(precio, categoria);
    }
}
