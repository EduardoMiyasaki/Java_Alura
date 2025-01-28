import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private String enderecoURL;

    public HttpResponse<String> consultarCEP(String cep) throws IOException, InterruptedException {

        enderecoURL = "https://viacep.com.br/ws/" + cep + "/json/";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.enderecoURL)).
                build();

        return client.send(request , HttpResponse.BodyHandlers.ofString());
    }
}
