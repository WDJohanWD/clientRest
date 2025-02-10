package com.example.api;

public class MonedasIgualesException extends RuntimeException {
    public MonedasIgualesException() {
        super("Monedas iguales");
    }
}
