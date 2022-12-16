package com.tienda.backend.dto.carrito;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tienda.backend.dto.orden.GetAllOrdenDto;
import com.tienda.backend.dto.producto.GetAllProductoDto;

import java.util.List;

public class GetAllProductCarritoDto {
    public GetCarritoDto carrito;

    @JsonProperty("ordenes")
    public List<GetAllOrdenDto> productos;
}