package com.secomext.service;

import com.secomext.model.Product;
import com.secomext.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Crear un nuevo producto
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    // Actualizar un producto existente
    public Product updateProduct(Long id, Product updatedProduct) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setCategory(updatedProduct.getCategory());
                    return repository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Eliminar un producto por ID
    public void deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }
}
