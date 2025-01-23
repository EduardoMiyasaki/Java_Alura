import java.util.Scanner;

public class LoopWhile {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double media = 0;
        double nota = 0;
        int totalNotas = 0;

        // poderia utilizar while(true)
        while (nota >= 0) {

            System.out.print("Diga sua avaliação para o filme ou -1 para encerrar:");

            nota = teclado.nextDouble();

            if (nota >= 0) {

                media += nota;
                totalNotas++;
            }
            else {
                break;
            }

        }

        media = media / totalNotas;
        System.out.println(String.format("A avaliação média é de %.2f", media));
    }
}

