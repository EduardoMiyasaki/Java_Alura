package exercicio2;

public class Pessoa {

    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.length() < 8) {
            throw new SenhaInvalidaException("Senha deve ter pelo menos 8 caracteres");
        }
        this.senha = senha;
    }
}
