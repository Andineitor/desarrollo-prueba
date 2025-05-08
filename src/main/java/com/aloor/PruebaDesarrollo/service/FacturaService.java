package com.aloor.PruebaDesarrollo.service;

import com.aloor.PruebaDesarrollo.entity.Factura;
import com.aloor.PruebaDesarrollo.entity.Producto;


public interface FacturaService {

	Factura calcularFactura(Producto producto) throws Exception;
}
