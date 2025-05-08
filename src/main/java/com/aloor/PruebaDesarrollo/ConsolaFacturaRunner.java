package com.aloor.PruebaDesarrollo;

import com.aloor.PruebaDesarrollo.entity.Categoria;
import com.aloor.PruebaDesarrollo.entity.Factura;
import com.aloor.PruebaDesarrollo.entity.Producto;
import com.aloor.PruebaDesarrollo.repository.CategoriaRepository;
import com.aloor.PruebaDesarrollo.repository.ProductoRepository;
import com.aloor.PruebaDesarrollo.service.FacturaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsolaFacturaRunner implements CommandLineRunner {

    private final CategoriaRepository categoriaRepo;
    private final FacturaService facturaService;
    private final ProductoRepository productoRepo;

    public ConsolaFacturaRunner(CategoriaRepository categoriaRepo, FacturaService facturaService, ProductoRepository productoRepo) {
        this.categoriaRepo = categoriaRepo;
        this.facturaService = facturaService;
        this.productoRepo = productoRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Nuevo Pedido ---");
            
        System.out.println("--- Creador de Factura desde Consola ---");

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        List<Categoria> categorias = categoriaRepo.findAll();
        System.out.println("Seleccione la categoría:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.printf("%d. %s (%s%% descuento)%n", i + 1, categorias.get(i).getNombre(), categorias.get(i).getPorcentajeDescuento() * 100);
        }

        System.out.print("Ingrese el número de categoría: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (opcion < 1 || opcion > categorias.size()) {
            System.out.println(" Categoría inválida. Abortando.");
            return;
        }

        Categoria categoriaSeleccionada = categorias.get(opcion - 1);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecioOriginal(precio);
        producto.setCategoria(categoriaSeleccionada);
        producto = productoRepo.save(producto);
        try {
            Factura factura = facturaService.calcularFactura(producto);

            System.out.println("\n Factura generada correctamente:");
            System.out.println("----------------------------------");
            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Categoría: " + categoriaSeleccionada.getNombre());
            System.out.println("Precio original: $" + producto.getPrecioOriginal());
            System.out.println("Precio con descuento: $" + factura.getPrecioConDescuento());
            System.out.println("Costo de envío: $" + factura.getCostoEnvio());
            System.out.println("Precio final: $" + factura.getPrecioFinal());
            System.out.println("----------------------------------");

        } catch (Exception e) {
            System.out.println(" Error al generar la factura: " + e.getMessage());
        }
        System.out.print("\n¿Desea hacer otro pedido? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (!respuesta.equals("s")) {
            System.out.println("👋 ¡Gracias por usar el generador de facturas!");
            break;
        }
        }
    }
}
