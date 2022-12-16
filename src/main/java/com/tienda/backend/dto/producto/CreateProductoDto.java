package com.tienda.backend.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProductoDto {
    @JsonProperty("nombre")
    public String nom_pro;

    @JsonProperty("descripcion")
    public String des_pro;

    @JsonProperty("imagen")
    public String ima_pro;

    @JsonProperty("categoria")
    public String nom_cate;

    @JsonProperty("usuario")
    public Integer usu_id;

    @JsonProperty("cantidad")
    public Integer can_pro;

    @JsonProperty("precio-unitario")
    public Integer pre_pro;
}