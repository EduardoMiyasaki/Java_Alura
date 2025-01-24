package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculo.Classificacao;

public class Episodio implements Classificacao {

    private int numero;
    private String nome;
    private Serie serie;
    private int totalVisu;


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getTotalVisu() {
        return totalVisu;
    }

    public void setTotalVisu(int totalVisu) {
        this.totalVisu = totalVisu;
    }

    @Override
    public int getClassificao() {
      if(totalVisu > 100){
          return 4;
      }
      else{
          return 2;
      }
    }
}
