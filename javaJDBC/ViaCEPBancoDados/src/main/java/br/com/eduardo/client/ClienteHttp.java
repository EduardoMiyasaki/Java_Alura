package br.com.eduardo.client;

import br.com.eduardo.CepException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {

    private String enderecoURL;

    public HttpResponse<String> consultarCEP(String cep) {

        this.enderecoURL = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoURL))
                    .build();

            return cliente.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new CepException("Cep inválido");
        }
    }
}
