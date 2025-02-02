package br.com.alura.domain;

import java.util.Arrays;

public class Abrigo {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo(AbrigoDTO abrigoDTO) {
        this.nome = abrigoDTO.nome();
        this.telefone = abrigoDTO.telefone();
        this.email = abrigoDTO.email();
    }

    // Construtor default para o Jackson conseguir deserializar os JSON
    public Abrigo() {}

    public Abrigo(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", pets ='" + Arrays.stream(pets).toList() + '\'' +
                '}';
    }
}
