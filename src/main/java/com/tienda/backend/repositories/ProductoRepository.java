package com.tienda.backend.repositories;

import com.tienda.backend.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(value = "SELECT * FROM productos p WHERE p.pro_cate = :nombreCategoria", nativeQuery = true)
    List<Producto> findByCategoria(String nombreCategoria);
}
