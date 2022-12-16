package com.tienda.backend.dto.orden;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tienda.backend.dto.producto.GetAllProductoDto;

public class GetAllOrdenDto {
    @JsonProperty("id")
    public Integer id_ord;

    public GetAllProductoDto producto;
    @JsonProperty("cantidad-total")
    public Integer can_ord;
    @JsonProperty("precio-total")
    public Integer total_ord;
}