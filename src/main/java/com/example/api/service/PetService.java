package com.example.api.service;

import org.springframework.web.client.RestClient;

public class PetService {
    private RestClient restClient;

    public PetService() {
        this.restClient = RestClient.create("Aaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}