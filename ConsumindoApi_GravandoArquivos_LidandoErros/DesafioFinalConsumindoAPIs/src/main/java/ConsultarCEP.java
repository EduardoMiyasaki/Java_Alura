import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarCEP {

    private String url;

    private HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> consultarCEP(String cep) throws IOException, InterruptedException {

        url = "https://viacep.com.br/ws/" + cep + "/json/";

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
