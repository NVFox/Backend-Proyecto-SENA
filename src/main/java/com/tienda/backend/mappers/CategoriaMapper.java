package com.tienda.backend.mappers;

import com.tienda.backend.dto.categoria.CreateCategoriaDto;
import com.tienda.backend.dto.categoria.GetCategoriaDto;
import com.tienda.backend.entities.Categoria;
import com.tienda.backend.services.categoria.ICategoriaService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoriaMapper {
    @Autowired
    private ICategoriaService categoriaService;

    public abstract List<GetCategoriaDto> toGetCategoryDtoList(List<Categoria> categorias);

    @Mapping(target = "productos", ignore = true)
    public abstract Categoria toCategoria(CreateCategoriaDto createCategoriaDto);

    public abstract GetCategoriaDto toGetCategoriaDto(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productos", ignore = true)
    public abstract void updateCategoriaFromDto(CreateCategoriaDto dto, @MappingTarget Categoria categoria);

    @Named("stringToCategoria")
    public Categoria stringToCategoria(String nombre) {
        return categoriaService.encontrarPorId(nombre);
    }

    @Named("categoriaToString")
    public String categoriaToString(Categoria categoria) {
        return categoria.nom_cate;
    }
}
