package com.tienda.backend.mappers;

import com.tienda.backend.dto.orden.GetAllOrdenDto;
import com.tienda.backend.entities.Orden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = ProductoMapper.class, componentModel = "spring")
public interface OrdenMapper {
    List<GetAllOrdenDto> toGetAllOrdenDtoList(List<Orden> ordenes);

    @Mapping(source = "id_pro", target = "producto")
    GetAllOrdenDto toGetAllOrdenDto(Orden orden);
}