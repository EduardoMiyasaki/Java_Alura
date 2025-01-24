package br.com.alura.screenmatch.calculo;

import br.com.alura.screenmatch.model.Titulo;

public class Recomendacao {

    private String recomandacao;

    public void filtrar(Classificacao classificacao) {
        if (classificacao.getClassificao() >= 4) {
            System.out.println("Está entre os preferidos");
        }
        else {
            System.out.println("Filme ruim");
        }

    }
}
