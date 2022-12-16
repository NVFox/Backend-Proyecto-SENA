package com.tienda.backend.services.auth;

import com.tienda.backend.dto.auth.PostAuthUserDto;
import com.tienda.backend.entities.Usuario;
import com.tienda.backend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private IUsuarioService usuarioService;

    @Autowired
    public AuthService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario login(PostAuthUserDto authUserDto) {
        return usuarioService
                .encontrarUsuarioPorNombreYPass(authUserDto.name, authUserDto.pass);
    }
}
