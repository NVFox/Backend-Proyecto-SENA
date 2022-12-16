package com.tienda.backend.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostAuthUserDto {
    @JsonProperty("nombre")
    public String name;

    @JsonProperty("contraseña")
    public String pass;
}
