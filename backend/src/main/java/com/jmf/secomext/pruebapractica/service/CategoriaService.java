package com.jmf.secomext.pruebapractica.service;

import com.jmf.secomext.pruebapractica.entity.Categoria;
import com.jmf.secomext.pruebapractica.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        categoria.setNombre(categoriaActualizada.getNombre());
        return categoriaRepository.save(categoria);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        return categoriaRepository.save(categoria);
    }
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
