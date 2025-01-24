public class Musica {

    String titulo;
    String artista;
    int anoLancamento;
    double avaliacao;
    int numAvaliacoes;

    String exibirFicha() {
        return "O Título da música é" + this.titulo + " o nome do artista é " + this.artista;
    }

    void avaliar(double nota) {
        this.avaliacao += nota;
        numAvaliacoes++;
    }

    double calcularMedia() {
        return avaliacao / numAvaliacoes;
    }
}
