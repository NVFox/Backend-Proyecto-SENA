package com.tienda.backend.dto.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCategoriaDto {
    @JsonProperty("nombre")
    public String nom_cate;
    @JsonProperty("descripcion")
    public String des_cate;
}
