package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.comparadores.ComparadorAno;
import br.com.alura.screenmatch.model.Movie;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.Titulo;

import java.util.*;

public class MainComListas {

    public static void main(String[] args) {

        Movie favorito = new Movie("The Matrix", 1999);
        Movie filme1 = new Movie("Mad max", 2023);
        var filmeDoPaulo = new Movie("DogVille", 2003);

        Serie serie = new Serie("Lost", 2000);

        // Variável de referência
        // Isso significa que f1 não é um novo objeto de Filme,
        // mas sim outra referência ao mesmo objeto apontado por filmeDoPaulo.
        // Se eu alterar o estado do objeto por meio de f1, essas mudanças
        // também serão refletidas em filmeDoPaulo, pois ambos apontam para o mesmo objeto

        Movie f1 = filmeDoPaulo;
        System.out.println("Oiiiiiiiiiiiiiiiiiiiiiiiii" + f1.getNome());
        System.out.println("Oiiiiiiiiiiiiiiiiiiiiiiiii" + filmeDoPaulo.getNome());


        // do mesmo jeito que um metódo pode ter como parametro a classe mãe e aceitar as filhas
        // uma lista também :)

        List<Titulo> lista = new LinkedList<>();

        lista.add(filmeDoPaulo);
        lista.add(filme1);
        lista.add(favorito);

        lista.add(serie);

        filmeDoPaulo.avalia(9);
        filme1.avalia(3);
        favorito.avalia(6);

        System.out.println("ForEach antigo");
        for (Titulo item : lista) {
            System.out.println(item);
        }

        System.out.println("-----------------------------------------");
        // outro forEach
        // lista.forEach(System.out::println);
        System.out.println("ForEach novo");

//        lista.forEach(titulo -> {
//            System.out.println(titulo);
//
//           Movie filme = (Movie) titulo;
//
//            // Deu excepition pois série não é um filme e no casting acima
//            // Eu afirmei que todo titulo seria um Filme então da esse "Problema";
//
//            System.out.println("Classificação: " + filme.getClassificao());
//        });


        lista.forEach(titulo -> {

            System.out.println(titulo.getNome());
            // posso fazer verificação ainda
            if (titulo instanceof Movie filmeForEach) {
                System.out.println("Classificação: " + filmeForEach.getClassificao());
            }

        });

        ArrayList<String> buscaPorArtista = new ArrayList<>();

        buscaPorArtista.add("Adam sandler");
        buscaPorArtista.add("Eduardo Miyasaki");
        buscaPorArtista.add("Cléber machado");

        System.out.println(buscaPorArtista);

        // Ordendando em ordem alfabética
        Collections.sort(buscaPorArtista);
        System.out.println("Depois de ordernar ficou assim");
        System.out.println(buscaPorArtista);

        // Antes da ordenação
        System.out.println(lista);

        // Não da para ordenar desta maneira pois falta um critério de avaliação especificado
        // Não é uma String para ordenar de maneira alfabética nem número do menor para o maior ou vice versa

        // Para fazer desta maneira temos que Reescrever o método de comparação da interface Comparable

        System.out.println("Comparando pela ordem alfabética");
        Collections.sort(lista);
        System.out.println(lista);

        // Comparando de acordo com a ordem da lançamento menor para o Maior
        ComparadorAno comparadorAno = new ComparadorAno();
        lista.sort(comparadorAno);

        // outra maneira
        // Collections.sort(lista , comparadorAno);
        System.out.println("Ordenado pelo ano de lançamento");
        System.out.println("----------------------");
        System.out.println(lista);

        // Maneira mais moderna
        lista.sort(Comparator.comparing(Titulo::getNome));
        System.out.println(lista);


    }
}
