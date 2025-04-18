package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculo.Classificacao;

public class Movie extends Titulo implements Classificacao {

    private String diretor;

    public Movie(String nome , int anoDeLancamento){
        super(nome, anoDeLancamento);

    }

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


    @Override
    public String toString() {
        return "Nome do filme:" + this.getNome() + " (" + this.getAnoDeLancamento() + ")";
    }
}