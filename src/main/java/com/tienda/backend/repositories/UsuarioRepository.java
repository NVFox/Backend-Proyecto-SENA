package com.tienda.backend.repositories;

import com.tienda.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Modifying
    @Query("update Usuario u set u.sal_usu = :saldo where u.id_usu = :usuario")
    void updateSaldoUsuario(int usuario, int saldo);

    @Query("select u from Usuario u where u.nom_usu = :nombre and u.con_usu = :pass")
    Optional<Usuario> findUsuarioByNombreAndPass(String nombre, String pass);
}
