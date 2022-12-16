package com.tienda.backend.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tienda.backend.entities.Categoria;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GetAllProductoDto {
    @JsonProperty("id")
    public int id_pro;
    @JsonProperty("nombre")
    public String nom_pro;

    @JsonProperty("imagen")
    public String ima_pro;

    @JsonProperty("descripcion")
    public String des_pro;

    @JsonProperty("categoria")
    public String pro_cate;

    @JsonProperty("cantidad")
    public int can_pro;

    @JsonProperty("precio-unitario")
    public int pre_pro;
}