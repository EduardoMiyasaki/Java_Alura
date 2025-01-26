package exercicio2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.coingecko.com/api/v3/ping?x_cg_demo_api_key=CG-LN8ZpSx3t9udCtQDxnNHmJTb"))
                .build();

        HttpResponse<String> response = client.send(request , HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
