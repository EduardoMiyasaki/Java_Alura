package exercicio4;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import exercicio3.GsonField;
import exercicio3.HTTP.HttpCliente;
import exercicio3.Pessoa;

import java.io.IOException;
import java.net.http.HttpResponse;

public class GitHubUser extends Pessoa {

    private String cidade;
    private int id;
    private String enderecoURL;

    public GitHubUser(UserRecord userRecord) {
        this.setNome(userRecord.login());
        this.cidade = userRecord.location();
        this.id = userRecord.id();
    }

    public GitHubUser(String nome) {
        this.setNome(nome);
        this.enderecoURL = "https://api.github.com/users/" + this.getNome();
    }

    public int getId() {
        return id;
    }

    public GitHubUser consultarUsuario(String nome) throws IOException, InterruptedException {

        Gson gson = new Gson().
                newBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpCliente cliente = new HttpCliente(nome);
        HttpResponse<String> response = cliente.enviarRequisicao();

        String json = response.body();
        System.out.println(json);
        UserRecord userRecord = gson.fromJson(json, UserRecord.class);

        GitHubUser userGitHub = new exercicio4.GitHubUser(userRecord);

        if (userGitHub.getId() == 0) {
             throw new ErroConsultaGitHubException("Usuário não encontrado");
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

