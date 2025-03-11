package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.validacao.ValidacaoException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private final HttpClient client = HttpClient.newHttpClient();

    public String obterDados(String endereco) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ValidacaoException(e.getMessage());
        }
        return response.body();
    }
}