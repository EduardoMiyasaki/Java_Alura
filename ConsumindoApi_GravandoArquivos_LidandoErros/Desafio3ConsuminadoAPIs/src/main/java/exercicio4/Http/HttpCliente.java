package exercicio4.Http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpCliente {

    private String enderecoURL;

    public HttpCliente(String nome) throws IOException, InterruptedException {
        this.enderecoURL = "https://api.github.com/users/" + nome;
    }

    public HttpResponse<String> enviarRequisicao() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoURL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }


}

