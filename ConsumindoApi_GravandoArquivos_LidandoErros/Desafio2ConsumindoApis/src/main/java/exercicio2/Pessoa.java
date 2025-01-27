package exercicio2;

import exercicio1.PessoaRecord;

public class Pessoa {

    private String nome;
    private int idade;
    private String cidade;

    public Pessoa(PessoaRecord pessoa) {
        this.setNome(pessoa.nome());
        this.setCidade(pessoa.cidade());
        this.setIdade(pessoa.idade());
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private void setIdade(int idade) {
        this.idade = idade;
    }

    private void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCidade() {
        return cidade;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                Idade: %d
                Cidade: %s
                """, this.getNome(), this.getIdade(), this.getCidade());
    }
}
