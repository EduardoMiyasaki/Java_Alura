package br.com.alura.models;

public class Audio {

    private String titulo;
    private int duracao;
    private int totalReproducoes;
    private int curtidas;
    protected int classificacao;
    private boolean reproduzindo = false;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public int getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void reproduzir() {
        this.reproduzindo = true;
        this.totalReproducoes++;
    }

    public void pausar() {
        this.reproduzindo = false;
    }

    public void curtir() {
        this.curtidas ++;
    }
}
