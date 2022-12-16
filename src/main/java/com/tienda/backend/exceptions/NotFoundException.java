package com.tienda.backend.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("El elemento buscado no existe");
    }
}
