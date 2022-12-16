package com.tienda.backend.dto.orden;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrdenDto {
    @JsonProperty("producto")
    public int id_pro;
    @JsonProperty("usuario")
    public int id_usu;
    @JsonProperty("cantidad-total")
    public int can_total;
}
