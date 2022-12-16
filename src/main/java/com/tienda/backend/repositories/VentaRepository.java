package com.tienda.backend.repositories;

import com.tienda.backend.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("select v from Venta v JOIN v.ord_id o JOIN o.id_pro p JOIN p.usu_id u where u.id_usu = :usuario")
    List<Venta> findAllByUsuario(Integer usuario);
}