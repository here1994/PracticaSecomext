package com.secomext.service;

import com.secomext.model.Category;
import com.secomext.model.Product;
import com.secomext.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category createCategory(Category category) {
        // Asignar la categoría a cada producto antes de guardar
        if (category.getProducts() != null) {
            for (Product product : category.getProducts()) {
                product.setCategory(category);
            }
        }
        return repository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return repository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());

                    // Limpiar productos antiguos y asignar los nuevos
                    category.getProducts().clear();
                    if (updatedCategory.getProducts() != null) {
                        for (Product product : updatedCategory.getProducts()) {
                            product.setCategory(category);
                        }
                        category.getProducts().addAll(updatedCategory.getProducts());
                    }
                    return repository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }


    public void deleteCategory(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Categoría no encontrada");
        }
    }
}
