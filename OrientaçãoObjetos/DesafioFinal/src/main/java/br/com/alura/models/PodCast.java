package br.com.alura.models;

public class PodCast extends Audio implements Classificacao {

    private String apresentador;
    private String convidado;
    private String descricao;

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    public String getConvidado() {
        return convidado;
    }

    public void setConvidado(String convidado) {
        this.convidado = convidado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public double verAvaliacao() {
        int estrelas = 0;


        if (getTotalReproducoes() >= 100000) {
            estrelas = 5;
        } else if (getTotalReproducoes() >= 75000) {
            estrelas = 4;
        } else if (getTotalReproducoes() >= 50000) {
            estrelas = 3;
        } else {
            estrelas = 2;
        }

        return estrelas;
    }
}

