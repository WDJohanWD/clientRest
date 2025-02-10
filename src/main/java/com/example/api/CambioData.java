package com.example.api;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Objeto que se corresponde con la respuesta de la API con la URL https://api.frankfurter.dev/v1/latest
public class CambioData {
    private float amount;
    private String base;
    private String date;
    
    private HashMap<String, Float> rates;
}
