package com.tienda.backend.repositories;

import com.tienda.backend.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    @Query(value = "select c from Carrito c where c.status = :status")
    List<Carrito> findAllByStatus(String status);

    @Query(value = "select c from Carrito c JOIN c.id_usu u where u.id_usu = :id")
    List<Carrito> findAllByUsuario(Integer id);

    @Query(value = "select c from Carrito c JOIN c.id_usu u where u.id_usu = :id and c.status = :status")
    List<Carrito> findAllByUsuarioAndStatus(Integer id, String status);

    @Query(value = "select c from Carrito c JOIN c.id_usu u where u.id_usu = :id and c.status = 'ACTIVE'")
    Optional<Carrito> findByActiveCarrito(Integer id);
}