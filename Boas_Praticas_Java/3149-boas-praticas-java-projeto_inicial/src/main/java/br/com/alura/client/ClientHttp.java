package br.com.alura.client;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private HttpClient client;

    public ClientHttp() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> dispararRequisicaoGET(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).
                    build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public HttpResponse<String> dispararRequisicaoPost(Object object, String uri) {
// new Gson.toJson() como tem que enviar um JSON estamos transformando o objeto em String
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(new Gson().toJson(object)))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
