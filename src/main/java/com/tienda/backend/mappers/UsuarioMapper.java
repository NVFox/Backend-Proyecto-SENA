package com.tienda.backend.mappers;

import com.tienda.backend.dto.usuario.CreateUsuarioDto;
import com.tienda.backend.dto.usuario.GetUsuarioDto;
import com.tienda.backend.entities.Usuario;
import com.tienda.backend.services.usuario.IUsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    @Autowired
    private IUsuarioService usuarioService;

    public abstract List<GetUsuarioDto> toGetUsuarioDtoList(List<Usuario> usuario);

    public abstract GetUsuarioDto toGetUsuarioDto(Usuario usuario);

    @Mapping(target = "sal_usu", ignore = true)
    @Mapping(source = "rol_usu", target = "rol_usu")
    public abstract Usuario toUsuario(CreateUsuarioDto createUsuarioDto);

    @Mapping(target = "sal_usu", ignore = true)
    @Mapping(source = "rol_usu", target = "rol_usu")
    public abstract void updateUsuarioFromDto(CreateUsuarioDto dto, @MappingTarget Usuario usuario);

    @Named("intToUsuario")
    public Usuario intToUsuario(Integer id) {
        return usuarioService.encontrarPorId(id);
    }
}
