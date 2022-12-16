package com.tienda.backend.controllers;

import com.tienda.backend.dto.categoria.CreateCategoriaDto;
import com.tienda.backend.dto.categoria.GetCategoriaDto;
import com.tienda.backend.dto.usuario.CreateUsuarioDto;
import com.tienda.backend.dto.usuario.GetUsuarioDto;
import com.tienda.backend.dto.usuario.UpdateSaldoDto;
import com.tienda.backend.entities.Categoria;
import com.tienda.backend.entities.Usuario;
import com.tienda.backend.mappers.UsuarioMapper;
import com.tienda.backend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@CrossOrigin("*")
public class UsuarioController {
    private IUsuarioService usuarioService;
    private UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetUsuarioDto>> listarTodos() {
        return ResponseEntity.ok()
                .body(usuarioMapper.toGetUsuarioDtoList(usuarioService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUsuarioDto> encontrarUsuario(@PathVariable("id") int id) {
        return ResponseEntity.ok()
                .body(usuarioMapper.toGetUsuarioDto(usuarioService.encontrarPorId(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> agregarSaldo(
            @PathVariable("id") int id,
            @RequestBody UpdateSaldoDto updateSaldoDto
    ) {
        Usuario usuario = usuarioService.encontrarPorId(id);
        int nuevoSaldo = usuario.sal_usu + updateSaldoDto.saldo;

        usuarioService.actualizarSaldoUsuario(id, nuevoSaldo);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<GetUsuarioDto> crearUsuario(@RequestBody CreateUsuarioDto createUsuarioDto) {
        Usuario usuario = usuarioMapper.toUsuario(createUsuarioDto);
        Usuario createdUsuario = usuarioService.crear(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioMapper.toGetUsuarioDto(createdUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarUsuario(
            @RequestBody CreateUsuarioDto updateUsuarioDto,
            @PathVariable("id") int id
    ) {
        Usuario found = usuarioService.encontrarPorId(id);
        usuarioMapper.updateUsuarioFromDto(updateUsuarioDto, found);
        usuarioService.actualizar(found);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable("id") int id) {
        usuarioService.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
