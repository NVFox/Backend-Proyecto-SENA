package com.tienda.backend.services.usuario;

import com.tienda.backend.entities.Usuario;
import com.tienda.backend.services.IBaseService;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService extends IBaseService<Usuario, Integer> {
    void actualizarSaldoUsuario(int usuario, int nuevoSaldo);
    Usuario encontrarUsuarioPorNombreYPass(String nombre, String pass);
}
