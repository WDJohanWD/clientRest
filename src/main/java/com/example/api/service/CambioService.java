package com.example.api.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.api.domain.CambioData;
import com.example.api.exceptions.MonedasIgualesException;

@Service
public class CambioService {

    private RestClient restClient;

    public CambioService() {
        this.restClient = RestClient.create("http://api.frankfurter.dev/v1");
    }

    public Double calcularImporteCambiado(String monedaOrigen, String monedaDestino, Double importe) {
        Float tasaCambio = obtenerTasaCambio(monedaOrigen, monedaDestino);
        return importe * tasaCambio;
    }

    public Float obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws RuntimeException {
        if (monedaOrigen.equals(monedaDestino))
            throw new MonedasIgualesException();
        String url = "/latest?from=" + monedaOrigen + "&to=" + monedaDestino;

        // ResponseEntity<CambioData> recoge la respuesta de la API y la convierte en un objeto CambioData
        // de la URL https://api.frankfurter.dev/v1/latest?from=monedaOrigen&to=MonedaDestino
        ResponseEntity<CambioData> result = restClient.get()
                .uri(url)
                .retrieve()
                .toEntity(CambioData.class);
        return result.getBody().getRates().get(monedaDestino);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap<String, String> obtenerMonedas() {
        ResponseEntity<HashMap> result = restClient.get().uri("/currencies").retrieve().toEntity(HashMap.class);
        return result.getBody();
    }
}