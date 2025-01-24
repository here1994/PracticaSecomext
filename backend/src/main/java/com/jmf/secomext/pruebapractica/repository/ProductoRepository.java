package com.jmf.secomext.pruebapractica.repository;

import com.jmf.secomext.pruebapractica.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
}
