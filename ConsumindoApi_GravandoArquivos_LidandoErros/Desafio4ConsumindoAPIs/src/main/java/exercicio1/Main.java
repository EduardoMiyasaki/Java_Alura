package exercicio1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        // Criando um arquivo
        FileWriter escritor = new FileWriter("arquivo.txt");

        System.out.print("Informe o que quer escrever no arquivo: ");
        String textoArquivo = teclado.nextLine();

        escritor.write(textoArquivo);
        escritor.close();
    }
}
