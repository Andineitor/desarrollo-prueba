package com.aloor.PruebaDesarrollo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloor.PruebaDesarrollo.entity.Categoria;
import com.aloor.PruebaDesarrollo.entity.Producto;
import com.aloor.PruebaDesarrollo.repository.CategoriaRepository;
import com.aloor.PruebaDesarrollo.repository.ProductoRepository;
import com.aloor.PruebaDesarrollo.service.ProductoService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository producRepo;
	@Autowired
	private final CategoriaRepository cateRepo;

	

	@Override
	public Producto crearProducto(Double precio, String categoria) {
		Categoria categorias = cateRepo.findByNombreIgnoreCase(categoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
		
		double descuento = 0;
        if (categorias.getNombre().equalsIgnoreCase("Electrónicos")) {
            descuento = 0.10; 
        } else if (categorias.getNombre().equalsIgnoreCase("Ropa")) {
            descuento = 0.05; 
        }

       
        double precioConDescuento = precio - (precio * descuento);
        double costoEnvio = precioConDescuento > 50 ? 0 : 5;
        double precioFinal = precioConDescuento + costoEnvio;

		

        Producto producto = new Producto();
        producto.setPrecioOriginal(precio);
        producto.setCategoria(categorias);
        producto.setPrecioFinal(precioFinal);
        return producRepo.save(producto);
	}

}
