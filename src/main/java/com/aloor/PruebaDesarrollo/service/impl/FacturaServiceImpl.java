package com.aloor.PruebaDesarrollo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloor.PruebaDesarrollo.entity.Factura;
import com.aloor.PruebaDesarrollo.entity.Producto;
import com.aloor.PruebaDesarrollo.repository.FacturaRepository;
import com.aloor.PruebaDesarrollo.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
    private FacturaRepository facturaRepository;
	
	@Override
	public Factura calcularFactura(Producto producto) throws Exception {
		try {
            double descuento = producto.getCategoria().getPorcentajeDescuento();
            double precioConDescuento = producto.getPrecioOriginal() * (1 - descuento);
            double costoEnvio = precioConDescuento > 50 ? 0 : 5;
            double precioFinal = precioConDescuento + costoEnvio;

            Factura factura = new Factura();
            factura.setProducto(producto);
            factura.setPrecioConDescuento(precioConDescuento);
            factura.setCostoEnvio(costoEnvio);
            factura.setPrecioFinal(precioFinal);

            return facturaRepository.save(factura);
        } catch (Exception e) {
            throw new Exception("Error al calcular la factura: " + e.getMessage());
        }
    }
}