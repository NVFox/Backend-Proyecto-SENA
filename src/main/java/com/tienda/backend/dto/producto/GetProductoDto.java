package com.tienda.backend.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductoDto {
    @JsonProperty("id")
    public Integer id_pro;
    @JsonProperty("nombre")
    public String nom_pro;
}
