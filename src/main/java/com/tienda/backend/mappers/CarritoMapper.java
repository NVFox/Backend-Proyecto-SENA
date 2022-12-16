package com.tienda.backend.mappers;

import com.tienda.backend.dto.carrito.GetAllProductCarritoDto;
import com.tienda.backend.dto.carrito.GetCarritoDto;
import com.tienda.backend.entities.Carrito;
import com.tienda.backend.entities.Orden;
import com.tienda.backend.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = OrdenMapper.class, componentModel = "spring")
public interface CarritoMapper {
    List<GetCarritoDto> toGetCarritoDtoList(List<Carrito> carritos);

    @Mapping(source = "carrito", target = "carrito", qualifiedByName = "carritoToGetCarritoDto")
    @Mapping(source = "ordenes", target = "productos")
    GetAllProductCarritoDto toGetAllProductCarritoDto(Carrito carrito, List<Orden> ordenes);

    @Named("carritoToGetCarritoDto")
    GetCarritoDto toGetCarritoDto(Carrito carrito);
}