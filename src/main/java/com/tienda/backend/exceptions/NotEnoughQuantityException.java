package com.tienda.backend.exceptions;

public class NotEnoughQuantityException extends RuntimeException {
    public NotEnoughQuantityException() {
        super("La cantidad elegida no está disponible para este producto");
    }
}
