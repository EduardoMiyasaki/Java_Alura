package exercicio3;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitHubUser extends Pessoa {

    private String cidade;
    private int id;
    private String enderecoURL;

    public GitHubUser(userRecord userRecord) {
        this.setNome(userRecord.login());
        this.cidade = userRecord.location();
        this.id = userRecord.id();
    }

    public GitHubUser(String nome) {
        this.setNome(nome);
        this.enderecoURL = "https://api.github.com/users/" + this.getNome();
    }


    public GitHubUser consultarUsuario(String nome) throws IOException, InterruptedException {

        Gson gson = new Gson().
                newBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoURL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);
        userRecord userRecord = gson.fromJson(json, exercicio3.userRecord.class);

        GitHubUser userGitHub = new GitHubUser(userRecord);

        if (userGitHub.id == 0) {
            throw new ErroConsultaGitHubException("Nome de usuário não encontrado");
        }

        return userGitHub;
    }

    @Override
    public String toString() {
        return String.format("""
                ID do Usuário: %s
                Nome do Usuário: %s
                Endereço: %s
                """, this.id, this.getNome(), this.cidade);
    }
}
