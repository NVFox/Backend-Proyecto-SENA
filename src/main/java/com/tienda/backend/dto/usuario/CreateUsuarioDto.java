package com.tienda.backend.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tienda.backend.entities.Roles;

public class CreateUsuarioDto {
    @JsonProperty("id")
    public Integer id_usu;

    @JsonProperty("nombre")
    public String nom_usu;

    @JsonProperty("correo")
    public String cor_usu;

    @JsonProperty("contraseña")
    public String con_usu;

    @JsonProperty("imagen")
    public String ima_usu;
    @JsonProperty("rol")
    public String rol_usu;
}