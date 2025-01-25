package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculo.Classificacao;

public class Serie extends Titulo implements Classificacao {

    public Serie(String nome, int anoDeLancamento) {
        // Quando eu instancia uma série ele irá chamar o construtor da mãe/Super
        super(nome, anoDeLancamento);
    }

    private int temporada;
    private int episodiosPorTemporada;
    private boolean ativa;
    private int minutorProEpisodio;
    private int totalVisu;

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getMinutorProEpisodio() {
        return minutorProEpisodio;
    }

    public void setMinutorProEpisodio(int minutorProEpisodio) {
        this.minutorProEpisodio = minutorProEpisodio;
    }

    @Override
    public int getDuracaoEmMinutos() {
        return this.temporada * episodiosPorTemporada * minutorProEpisodio;
    }

    @Override
    public int getClassificao() {
        if (totalVisu >= 1000) {
            return 5;
        }
        return 2;
    }

    @Override
    public String toString() {
        return "Nome da Série:" + this.getNome() + " (" + this.getAnoDeLancamento() + ")";
    }
}
