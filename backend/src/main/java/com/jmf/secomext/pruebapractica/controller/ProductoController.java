package com.jmf.secomext.pruebapractica.controller;

import com.jmf.secomext.pruebapractica.entity.Producto;
import com.jmf.secomext.pruebapractica.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
