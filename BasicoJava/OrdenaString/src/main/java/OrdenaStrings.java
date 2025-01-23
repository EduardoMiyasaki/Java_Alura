import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaStrings {

    public static void main(String[] args) {

        // Criando uma lista de Strings
        List<String> listaPalavras = new ArrayList<String>();

        // Colocando strings dentro da lista
        listaPalavras.add("Alura Online");
        listaPalavras.add("Editora casa do código");
        listaPalavras.add("Caelum");

        // utilizando a outra classe com Comparator para ordenar
        // de acordo com o tamanho da palavta
        Comparator<String> comparador = new ComparadorPorTamanho();
        Collections.sort(listaPalavras, comparador);

        // outro jeito sem precisar usar a classe Colecction fazendo de forma direta
        // de acordo com o tamanho da palavra
        listaPalavras.sort(comparador);
        System.out.println(listaPalavras);

        // Utilizando a biblioteca Collections para ordenar em ordem alfabética
        Collections.sort(listaPalavras);
        System.out.println(listaPalavras);

        // For Each simples
//        for (String p : listaPalavras){
//            System.out.println(p);
//        }

        Consumer<String> consumidor = new ImprimeNaLinha();
        listaPalavras.forEach(consumidor);
    }
}
