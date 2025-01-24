package com.jmf.secomext.pruebapractica.service;
import com.jmf.secomext.pruebapractica.entity.Producto;
import com.jmf.secomext.pruebapractica.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setCategoria(productoActualizado.getCategoria());
        return productoRepository.save(producto);
    }

    public Producto guardarProducto(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new RuntimeException("Ya existe un producto con el nombre: " + producto.getNombre());
        }
        return productoRepository.save(producto);
    }
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
