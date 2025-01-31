package br.com.alura.domain;

public class Abrigo {

    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public Abrigo(AbrigoDTO abrigoDTO) {
        this.nome = abrigoDTO.nome();
        this.telefone = abrigoDTO.telefone();
        this.email = abrigoDTO.email();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                ", nome='" + nome + '\'' +
                '}';
    }
}
