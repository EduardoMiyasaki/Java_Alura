package br.com.eduardo.domain;

import br.com.eduardo.EnderecoDTO;

public class Endereco {

    private String CEP;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String estado;
    private String regiao;

    public Endereco(String cep) {
        this.CEP = cep;
    }

    public Endereco(EnderecoDTO endereco) {
        this.CEP = endereco.cep();
        this.rua = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.localidade();
        this.uf = endereco.uf();
        this.estado = endereco.estado();
        this.regiao = endereco.regiao();
    }

    public String getCEP() {
        return CEP;
    }

    @Override
    public String toString() {
        return "br.com.eduardo.domain.Endereco{" +
                "CEP='" + CEP + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", estado='" + estado + '\'' +
                ", regiao='" + regiao + '\'' +
                '}';
    }
}
