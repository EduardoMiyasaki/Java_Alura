package br.com.alura.screenmatch.comparadores;

import br.com.alura.screenmatch.model.Titulo;

import java.util.Comparator;

public class ComparadorAno implements Comparator<Titulo> {


    @Override
    public int compare(Titulo titulo1, Titulo titulo2) {
        if (titulo1.getAnoDeLancamento() > titulo2.getAnoDeLancamento()) {
            return 1;
        }
        else if (titulo1.getAnoDeLancamento() < titulo2.getAnoDeLancamento()) {
            return -1;
        }
        return 0;
    }
}
