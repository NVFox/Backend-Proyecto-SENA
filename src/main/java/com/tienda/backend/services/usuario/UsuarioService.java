package com.tienda.backend.services.usuario;

import com.tienda.backend.entities.Usuario;
import com.tienda.backend.exceptions.BadCredentialsException;
import com.tienda.backend.repositories.UsuarioRepository;
import com.tienda.backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, Integer> implements IUsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void actualizar(Usuario entity) {
        super.encontrarPorId(entity.id_usu);
        super.actualizar(entity);
    }

    @Override
    public void actualizarSaldoUsuario(int id, int nuevoSaldo) {
        Usuario usuario = encontrarPorId(id);
        usuario.sal_usu = nuevoSaldo;

        actualizar(usuario);
    }

    @Override
    public Usuario encontrarUsuarioPorNombreYPass(String nombre, String pass) {
        return usuarioRepository.findUsuarioByNombreAndPass(nombre, pass)
                .orElseThrow(BadCredentialsException::new);
    }
}