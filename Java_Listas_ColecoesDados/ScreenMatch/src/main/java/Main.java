import br.com.alura.screenmatch.calculo.CalculradoraTempo;
import br.com.alura.screenmatch.calculo.Recomendacao;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Movie;
import br.com.alura.screenmatch.model.Serie;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

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

        System.out.println("Média de avaliações do filme: " + favorito.pegaMedia());

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

        var filmeDoPaulo = new Movie();
        filmeDoPaulo.setNome("DogVille");
        filmeDoPaulo.setDuracaoEmMinutos(200);
        filmeDoPaulo.setAnoDeLancamento(2003);
        filmeDoPaulo.avalia(10);

        // poderia utilizar o var no começo
        ArrayList<Movie> listaFilmes = new ArrayList<>();

        listaFilmes.add(filmeDoPaulo);
        listaFilmes.add(filme1);
        listaFilmes.add(favorito);

        System.out.println("Tamanhho da lista: " + listaFilmes.size());
        // Pegando o nome do primeiro filme da lista,
        System.out.println("Primeiro filme : " + listaFilmes.get(0).getNome());
        System.out.println(listaFilmes);
        System.out.println("ToString do filme: " + listaFilmes.get(0).toString());





        // Testando alguns comandos por si só!!!!!!!!!!

        // Verificando se contem o filme do paulo
        System.out.println(listaFilmes.contains(filmeDoPaulo));

        // Dando um update na posição
        listaFilmes.set(0, filme1);
        System.out.println("Pirmeiro filme : " + listaFilmes.get(0).getNome());
        listaFilmes.set(1, filmeDoPaulo);

        List<Movie> listaPreferidos = new ArrayList<>();

        // intervalo de filme que irei pegar do indice 0 ao 1.
        // 2 se torna o  exclusivo por isso não é pego

        listaPreferidos = listaFilmes.subList(0, 2);

        System.out.println(listaPreferidos.get(0).getNome());
        System.out.println(listaPreferidos.get(1).getNome());

        // transformando a List em um ArrayList

        ArrayList<Movie> listaPreferidos1 = new ArrayList<>(listaFilmes.subList(0, 2));

        // Acabei!!!!!!!!


    }

}