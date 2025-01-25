package br.com.alura.screenmatch.calculo;

import br.com.alura.screenmatch.model.Titulo;

public class CalculradoraTempo {

    private int tempoTotal;

    public int getTempoTotal() {
        return this.tempoTotal;
    }

//    public void incluir(Movie filme){
//        this.tempoTotal += filme.getDuracaoEmMinutos();
//    }
//
//    public void incluir(Serie serie){
//        this.tempoTotal += serie.getDuracaoEmMinutos();
//    }


    // Tem como passar a mãe como paramêtro e seus filhos poderam ser adicionados neste paramêtro
    public void incluir(Titulo titulo){
        
        this.tempoTotal += titulo.getDuracaoEmMinutos();
    }
}
