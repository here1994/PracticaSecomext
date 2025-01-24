package com.jmf.secomext.pruebapractica.controller;


import com.jmf.secomext.pruebapractica.entity.Categoria;
import com.jmf.secomext.pruebapractica.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> guardarCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria nuevaCategoria = categoriaService.guardarCategoria(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
            return ResponseEntity.ok(categoriaActualizada);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public void eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }
}
