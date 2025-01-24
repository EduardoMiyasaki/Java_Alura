package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculo.Classificacao;

public class Movie extends Titulo implements Classificacao {

    private String diretor;

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public int getClassificao() {
        return (int) pegaMedia() / 2;
    }
}