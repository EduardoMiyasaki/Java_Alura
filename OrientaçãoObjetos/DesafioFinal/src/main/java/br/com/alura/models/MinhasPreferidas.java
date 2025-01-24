package br.com.alura.models;

public class MinhasPreferidas {

    public void incluir(Classificacao audio){
        if(audio.verAvaliacao() >= 4){
            System.out.println("Está entre as preferidas");
        }
        else {
            System.out.println("Famoso");
        }
    }
}
