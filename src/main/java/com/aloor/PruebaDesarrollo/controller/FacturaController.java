package com.aloor.PruebaDesarrollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aloor.PruebaDesarrollo.entity.Categoria;
import com.aloor.PruebaDesarrollo.entity.Factura;
import com.aloor.PruebaDesarrollo.entity.Producto;
import com.aloor.PruebaDesarrollo.repository.CategoriaRepository;
import com.aloor.PruebaDesarrollo.repository.ProductoRepository;
import com.aloor.PruebaDesarrollo.service.FacturaService;

@Controller
public class FacturaController {
	
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
    private FacturaService facturaService;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "factura_form";
    }

    @PostMapping("/calcular")
    public String calcularFactura(@ModelAttribute Producto producto, Model model) {
        try {
            //  Solución clave: recuperar categoría completa desde la DB
            Categoria categoriaCompleta = categoriaRepo.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            producto.setCategoria(categoriaCompleta);

            // 💾 Guardar producto antes de usarlo
            Producto productoGuardado = productoRepo.save(producto);

            // 🧠 Calcular factura usando producto persistido
            Factura factura = facturaService.calcularFactura(productoGuardado);

            model.addAttribute("factura", factura);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "factura_form";
    }
}