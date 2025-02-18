package br.com.alura.exercicio1.demo;

import br.com.alura.exercicio1.demo.model.Tarefa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

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

    }
}
