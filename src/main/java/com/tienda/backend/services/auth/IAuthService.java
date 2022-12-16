package com.tienda.backend.services.auth;

import com.tienda.backend.dto.auth.PostAuthUserDto;
import com.tienda.backend.dto.usuario.GetUsuarioDto;
import com.tienda.backend.entities.Usuario;

public interface IAuthService {
    Usuario login(PostAuthUserDto authUserDto);
}