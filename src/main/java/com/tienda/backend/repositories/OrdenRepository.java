package com.tienda.backend.repositories;

import com.tienda.backend.entities.Orden;
import com.tienda.backend.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    @Query("select o from Orden o JOIN o.id_car c where c.id_car = :id")
    List<Orden> findAllProductosFromCarrito(Integer id);
}