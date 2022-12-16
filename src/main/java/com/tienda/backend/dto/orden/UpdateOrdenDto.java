package com.tienda.backend.dto.orden;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateOrdenDto {
    @JsonProperty("id")
    public Integer id_ord;
    @JsonProperty("cantidad-total")
    public Integer can_total;
}