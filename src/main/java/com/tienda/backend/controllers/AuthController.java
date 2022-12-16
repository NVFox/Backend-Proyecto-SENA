package com.tienda.backend.controllers;

import com.tienda.backend.dto.auth.PostAuthUserDto;
import com.tienda.backend.dto.usuario.GetUsuarioDto;
import com.tienda.backend.mappers.UsuarioMapper;
import com.tienda.backend.services.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AuthController {
    private IAuthService authService;
    private UsuarioMapper usuarioMapper;

    @Autowired
    public AuthController(IAuthService authService, UsuarioMapper usuarioMapper) {
        this.authService = authService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    public ResponseEntity<GetUsuarioDto> login(@RequestBody PostAuthUserDto postAuthUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioMapper.toGetUsuarioDto(authService.login(postAuthUserDto)));
    }
}
