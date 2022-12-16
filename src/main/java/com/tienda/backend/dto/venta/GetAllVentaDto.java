package com.tienda.backend.dto.venta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tienda.backend.dto.orden.GetAllOrdenDto;

import java.time.LocalDate;

public class GetAllVentaDto {
    @JsonProperty("id")
    public int id_ven;
    @JsonProperty("orden")
    public GetAllOrdenDto ord_id;
    @JsonProperty("fecha")
    public String fec_ven;
}