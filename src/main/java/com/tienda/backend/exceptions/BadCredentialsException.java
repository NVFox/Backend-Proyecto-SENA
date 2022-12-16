package com.tienda.backend.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Las credenciales son incorrectas o no existen");
    }
}
