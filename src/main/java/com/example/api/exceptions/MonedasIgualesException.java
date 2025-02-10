package com.example.api.exceptions;

public class MonedasIgualesException extends RuntimeException {
    public MonedasIgualesException() {
        super("Monedas iguales");
    }
}
