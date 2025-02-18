package br.com.alura.ScreenMatch.service;

import br.com.alura.ScreenMatch.validacoes.ValidacaoException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private HttpClient client = HttpClient.newHttpClient();

    public String obterDados(String endereco) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)).
                build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new ValidacaoException(e.getMessage());
        }
    }
}
