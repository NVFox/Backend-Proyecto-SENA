package com.tienda.backend.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUsuarioDto {
    @JsonProperty("id")
    public int id_usu;
    @JsonProperty("nombre")
    public String nom_usu;
    @JsonProperty("correo")
    public String cor_usu;
    @JsonProperty("imagen")
    public String ima_usu;
    @JsonProperty("saldo")
    public Integer sal_usu;
    @JsonProperty("rol")
    public String rol_usu;
}