import br.com.alura.screenmatch.calculo.CalculradoraTempo;
import br.com.alura.screenmatch.calculo.Recomendacao;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Movie;
import br.com.alura.screenmatch.model.Serie;

import java.lang.module.FindException;

public class Main {

    public static void main(String[] args) {
        Movie favorito = new Movie();

        favorito.setNome("The Matrix");
        favorito.setAnoDeLancamento(1999);
        favorito.setDuracaoEmMinutos(135);
        favorito.setIncluidoNoPlano(true);

        favorito.exibeFichaTecnica();
        favorito.avalia(9);
        favorito.avalia(8);
        favorito.avalia(9);

        Movie filme1 = new Movie();

        filme1.setNome("Mad max");
        filme1.setAnoDeLancamento(2023);
        filme1.setDuracaoEmMinutos(190);
        filme1.setIncluidoNoPlano(true);

        filme1.exibeFichaTecnica();
        filme1.avalia(9);
        filme1.avalia(8);
        filme1.avalia(9);

        System.out.println("Média de avaliações do filme: " +favorito.pegaMedia());

        Serie serie = new Serie();

        serie.setNome("Lost");
        serie.setAnoDeLancamento(2000);
        serie.exibeFichaTecnica();
        serie.setTemporada(10);
        serie.setEpisodiosPorTemporada(10);
        serie.setMinutorProEpisodio(50);

        System.out.println("Duração para assitir lost: " + serie.getDuracaoEmMinutos());

        CalculradoraTempo calculadora = new CalculradoraTempo();

        calculadora.incluir(favorito);

        calculadora.incluir(filme1);
        calculadora.incluir(serie);
        System.out.println(calculadora.getTempoTotal());

        Recomendacao filtro = new Recomendacao();
        filtro.filtrar(filme1);

        Episodio episodio = new Episodio();

        episodio.setNumero(1);
        episodio.setSerie(serie);
        episodio.setTotalVisu(300);

        filtro.filtrar(episodio);
    }

}