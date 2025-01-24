package com.jmf.secomext.pruebapractica.repository;

import com.jmf.secomext.pruebapractica.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre); // Método para verificar duplicados
}