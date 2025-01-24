package br.com.alura.models;

public class Musica extends Audio implements Classificacao {

    private String album;
    private String cantor;
    private String genero;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public double verAvaliacao() {

        int estrelas = 0;
        int porcentagemLikes = (getCurtidas() / getTotalReproducoes()) * 100;

        if (porcentagemLikes >= 50) {
            estrelas = 5;
        } else if (porcentagemLikes >= 35) {
            estrelas = 4;
        } else if (porcentagemLikes >= 20) {
            estrelas = 3;
        } else {
            estrelas = 2;
        }

        return estrelas;
    }

}
