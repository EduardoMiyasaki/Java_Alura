package br.com.alura.screenmatch.comparadores;

import br.com.alura.screenmatch.model.Titulo;

import java.util.Comparator;

public class ComparadorAno implements Comparator<Titulo> {


    @Override
    public int compare(Titulo titulo1, Titulo titulo2) {
       return Integer.valueOf(titulo1.getAnoDeLancamento()).compareTo(Integer.valueOf(titulo2.getAnoDeLancamento()));
    }
}
