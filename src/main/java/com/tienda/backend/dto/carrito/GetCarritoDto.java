package com.tienda.backend.dto.carrito;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCarritoDto {
    @JsonProperty("id")
    public int id_car;
    @JsonProperty("precio-total")
    public int total_car;
}