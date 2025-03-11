package br.com.alura.gerenciador_pedido.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Fornecedor() {}
}
