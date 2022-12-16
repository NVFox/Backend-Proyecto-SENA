package com.tienda.backend.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("No tiene saldo suficiente para realizar esta compra");
    }
}
