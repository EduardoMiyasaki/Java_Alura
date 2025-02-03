package br.com.alura.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {

    private Long id;
    private String tipo;
    private String nome;
    private String raca;
    private int idade;
    private String cor;
    private Float peso;


    public Pet(String tipo, String nome, String raca, int idade, String cor, Float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public Pet(PetDTO petDTO) {
        this.tipo = petDTO.tipo();
        this.nome = petDTO.nome();
        this.raca = petDTO.raca();
        this.idade = petDTO.idade();
        this.cor = petDTO.cor();
        this.peso = petDTO.peso();
    }

    // Construtor default para o Jackson conseguir deserializar os JSON
    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public Float getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "{" +
                "\"tipo\":\"" + tipo + "\"," +
                "\"nome\":\"" + nome + "\"," +
                "\"raca\":\"" + raca + "\"," +
                "\"idade\":" + idade +
                "}";
    }
}
