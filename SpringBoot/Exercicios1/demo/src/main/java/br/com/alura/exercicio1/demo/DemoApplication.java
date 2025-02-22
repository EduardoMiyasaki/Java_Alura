package br.com.alura.exercicio1.demo;

import br.com.alura.exercicio1.demo.exercicio3.Pessoa;
import br.com.alura.exercicio1.demo.exercicio3.Produto;
import br.com.alura.exercicio1.demo.model.Tarefa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Insira um número: ");
        var n1 = teclado.nextInt();

        for (int i = 1; i <= n1; i++) {
            System.out.println(i);
        }

        ObjectMapper conversor = new ObjectMapper();

        Tarefa tarefa = new Tarefa("Matemática1", true, "Eduardo");

        conversor.writeValue(new File("Tarefas.json"), tarefa);
        System.out.println("Dados salvo no arquivo");

        FileReader leitor = new FileReader("tarefa.json");
        var tarefa1 = conversor.readValue(leitor, Tarefa.class);
        System.out.println("-------------");
        System.out.println(tarefa1);
        leitor.close();

//                                            Lista 3
        System.out.println("----------------- Lista 3 ------------------");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        numeros.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        List<String> palavras = Arrays.asList("java", "stream", "lambda");
        palavras.stream()
                .map(p -> p.toUpperCase())
                .forEach(System.out::println);

        //  filtre os números ímpares
        //  multiplique cada um por 2 e colete os resultados em uma nova lista.
        List<Integer> numeros1 = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> listaImpares = numeros1.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println(listaImpares);

        List<String> palavras1 = Arrays.asList("apple", "banana", "apple", "orange", "banana");

        palavras1.stream()
                .distinct()
                .forEach(System.out::println);

        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );
        pessoas.stream()
                .filter(p -> p.getIdade() >= 18)
                .map(Pessoa::getNome)
                .sorted()
                .forEach(System.out::println);

        /*
            Filtre todos os produtos da categoria "Eletrônicos" com preço menor que R$ 1000,
            ordene-os pelo preço em ordem crescente e colete o resultado em uma nova lista.*/
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        List<Produto> eletronicosAbaixo1000 = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("Eletrônicos")
                        && p.getPreco() <= 1000)
                .sorted(Comparator.comparing(Produto::getPreco))
                .collect(Collectors.toList());

        System.out.println(eletronicosAbaixo1000);

        // mostre apenas os três produtos mais baratos da categoria "Eletrônicos".
        produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("Eletrônicos"))
                .sorted(Comparator.comparing(Produto::getPreco))
                .limit(3)
                .forEach(System.out::println);

        produtos.sort(Comparator.comparing(Produto::getPreco));


        //                                    Lista 4
        System.out.println("----------------- Lista 4 ------------------");

        List<Integer> numeros4 = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> est = numeros4.stream().max(Integer::compare);

        est.ifPresent(System.out::println);

        List<String> palavras4 = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> a = palavras.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(a);

        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");

        List<String> b = nomes.stream().
                collect(Collectors.toList());

        System.out.println(b);

        List<Integer> numeros41 = Arrays.asList(1, 2, 3, 4, 5, 6);

        int somaDosQuadrados = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println(somaDosQuadrados);

        Map<Boolean, List<Integer>> particionado = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Pares: " + particionado.get(true));  // Esperado: [2, 4, 6]
        System.out.println("Ímpares: " + particionado.get(false));

        Map<String, List<Produto>> agrupandoPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println(agrupandoPorCategoria);

        Map<String, Long> qntdProdutosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));

        System.out.println(qntdProdutosPorCategoria);

        Map<String, Optional<Produto>> maisCaroPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));

        System.out.println(maisCaroPorCategoria);

       Map<String, Double> somaProdutosPorCategoria = produtos.stream()
               .collect(Collectors.groupingBy(Produto::getCategoria,
                       Collectors.summingDouble(Produto::getPreco)));

        System.out.println(somaProdutosPorCategoria);
    }
}
