package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormInfo {
    private Double importe;
    private String monedaOrigen;
    private String monedaDestino;
}
